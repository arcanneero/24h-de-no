import { Component } from '@angular/core';
import { ToasterConfig } from 'angular2-toaster';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ihm';

  public toasterConfig = new ToasterConfig({
    animation: 'slideDown',
    positionClass: 'toast-top-center',
    timeout: { info: 2000, error: 5000 }
  });
}
