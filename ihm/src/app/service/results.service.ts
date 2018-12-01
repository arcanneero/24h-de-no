import {Injectable, NgZone} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import ReconnectingEventSource from 'reconnecting-eventsource';
import { Subscriber } from 'rxjs';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/share';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/take';
import 'rxjs/add/operator/last';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/takeUntil';
import 'rxjs/add/observable/interval';
import 'rxjs/add/observable/merge';
import 'rxjs/add/operator/withLatestFrom';
import 'rxjs/add/operator/distinctUntilChanged';

import {ResultsModel} from '../model/results';
import {MessageService} from './message.service';


@Injectable()
export class ResultsService {

  private eventSource: ReconnectingEventSource;

  private _results$: BehaviorSubject<ResultsModel> = new BehaviorSubject<ResultsModel>(new ResultsModel());
  private _results: ResultsModel = new ResultsModel();

  private _distance$: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  private _nbTour$: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  private _resultat$: BehaviorSubject<number> = new BehaviorSubject<number>(0);


  get results(): Observable<ResultsModel> {
    return this._results$;
  }

  get distance(): Observable<number> {
    return this._distance$;
  }

  get nbTour(): Observable<number> {
    return this._nbTour$;
  }

  get resultat(): Observable<number> {
    return this._resultat$;
  }

  constructor(
    private http: HttpClient,
    private zone: NgZone,
    private messageService: MessageService) {

    this.eventSource = new ReconnectingEventSource('http://ec2-52-47-143-54.eu-west-3.compute.amazonaws.com:8080/resultats');

    this._currentResults() // Etat initial
      .subscribe(games => {
        console.log(games);
        this._results = ResultsModel.of(games);
        this._results$.next(this._results);

        this._distance$.next(this._results.distance);
        this._nbTour$.next(this._results.nbTour);
        this._resultat$.next(this._results.results);

        this.subscribeToEvents() // Events venant des SSE
          .subscribe(event => {
            console.log('subscribeToEvents.subscribe', event);
            this._results = ResultsModel.of(event.resultats);
            this._results$.next(this._results);
            this._distance$.next(this._results.distance);
            this._nbTour$.next(this._results.nbTour);
            this._resultat$.next(this._results.results);
          });
      }, _ => { this.messageService.error(`Impossible de lister les résultats `, -1); });
  }

  private _currentResults() {
    return this.http.get<ResultsModel[]>('http://ec2-52-47-143-54.eu-west-3.compute.amazonaws.com:8080/admin');
  }

  private subscribeToEvents(): Observable<any> {
    return Observable.create((observer) => {
      this._subscribeToEvent('ResultatEvents', observer);
      // un observable ne peut être en erreur qu'une seule fois
      // this.eventsSource.onerror = (error) => observer.error(error);
    });
  }

  private _subscribeToEvent<T>(type: string, observer: Subscriber<T>) {
    console.log('Souscription aux évènements', type);
    this.eventSource.addEventListener(type, (event) => {
      console.log('Evènement reçu', type);
      this.zone.run(() => {
        const message: MessageEvent = event as MessageEvent;
        console.log(type + `.data: `, message.data);
        const data = JSON.parse(message.data);
        observer.next(data);
      });
    });
  }
}
