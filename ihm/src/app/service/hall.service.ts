import {Injectable, NgZone} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
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
import ReconnectingEventSource from 'reconnecting-eventsource';
import { Subscriber } from 'rxjs';

import {PlayerModel} from '../model/player';
import {MessageService} from './message.service';


@Injectable()
export class HallService {

  private eventSource: ReconnectingEventSource;

  private _players$: BehaviorSubject<PlayerModel[]> = new BehaviorSubject<PlayerModel[]>([]);
  private _players: PlayerModel[] = [];

  get players(): Observable<PlayerModel[]> {
    return this._players$;
  }


  constructor(
    private http: HttpClient,
    private zone: NgZone,
    private messageService: MessageService) {

    this.eventSource = new ReconnectingEventSource('http://ec2-52-47-143-54.eu-west-3.compute.amazonaws.com:8080/resultats');

    this._currentResults() // Etat initial
      .subscribe(players => {
        console.log(players);
        this._players = players.map(PlayerModel.of);
        this._players$.next(this._players);

        this.subscribeToEvents() // Events venant des SSE
          .subscribe(event => {

            console.log('subscribeToEvents.subscribe', event);
            this._players = event.map(PlayerModel.of);
            this._players$.next(this._players);
          });
      }, _ => { this.messageService.error(`Impossible de lister les joueurs`, -1); });
  }

  private _currentResults() {
    return this.http.get<PlayerModel[][]>('http://ec2-52-47-143-54.eu-west-3.compute.amazonaws.com:8080/admin/joueurs');
  }

  private subscribeToEvents(): Observable<any> {
    return Observable.create((observer) => {
      this._subscribeToEvent('HallEvents', observer);
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
