import { Injectable } from '@angular/core';
import { Toast, ToasterService } from 'angular2-toaster';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private toasterService: ToasterService) {
  }

  /**
   * Affiche un message d'erreur (sans titre)
   * @param message Le message
   * @package timeout Le temps d'affichage (-1 pour infinie)
   */
  error(message: string, timeout: number = 7000) {
    console.error('[ERROR]:', message);
    const toast: Toast = {
      type: 'error',
      body: message,
      timeout: timeout
    };
    this.toasterService.pop(toast);
  }

  /**
   * Affiche un message d'information (sans titre)
   * @param message Le message
   */
  info(message: string) {
    console.log('[INFO]:', message);
    const toast: Toast = {
      type: 'info',
      body: message,
      timeout: 3000
    };
    this.toasterService.pop(toast);
  }

}
