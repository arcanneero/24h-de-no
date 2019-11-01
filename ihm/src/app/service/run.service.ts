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

import {MessageService} from './message.service';
import {RunModel} from '../model/run';


@Injectable()
export class RunService {

  private eventSource: ReconnectingEventSource;

  private _runners$: BehaviorSubject<RunModel[]> = new BehaviorSubject<RunModel[]>([]);
  private _runners: RunModel[] = [];

  get runners(): Observable<RunModel[]> {
    return this._runners$;
  }


  constructor(
    private http: HttpClient,
    private zone: NgZone,
    private messageService: MessageService) {

    this.eventSource = new ReconnectingEventSource('http://ec2-52-47-143-54.eu-west-3.compute.amazonaws.com:8080/resultats');

    this._currentResults() // Etat initial
      .subscribe(players => {
        console.log(players);
        this._runners = players.map(RunModel.of);
        this._runners$.next(this._runners);

        this.subscribeToEvents() // Events venant des SSE
          .subscribe(event => {
            console.log('subscribeToEvents.subscribe', event);
            this._runners = event.pistes.map(RunModel.of);
            this._runners$.next(this._runners);
          });
      }, _ => { this.messageService.error(`Impossible de lister les pistes`, -1); });
  }

  private _currentResults() {
    return this.http.get<RunModel[][]>('http://ec2-52-47-143-54.eu-west-3.compute.amazonaws.com:8080/pistes');
  }

  private subscribeToEvents(): Observable<any> {
    return Observable.create((observer) => {
      this._subscribeToEvent('RaceEvents', observer);
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
