import { Injectable, NgZone } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import ReconnectingEventSource from 'reconnecting-eventsource';
import { Subscriber } from 'rxjs';

@Injectable()
export class CommonsService {

    private eventSource: ReconnectingEventSource;
    private readonly baseServerUrl: string;

    constructor(private zone: NgZone) {
        this.baseServerUrl = `http://ec2-52-47-143-54.eu-west-3.compute.amazonaws.com:8080/`;
        this.eventSource = new EventSource(this.buildUrl('/events'));
    }

    /**
     * Construit une url vers une resource sur le serveur
     */
    public buildUrl(path: string): string {
        return this.baseServerUrl + path;
    }


    public subscribeToEvent(eventName: string): Observable<any> {
      return Observable.create((observer) => {
        this._subscribeToEvent(eventName, observer);
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
