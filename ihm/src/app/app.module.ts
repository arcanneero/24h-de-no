import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TplHeaderComponent } from './component/tpl-header/tpl-header.component';
import { TplFooterComponent } from './component/tpl-footer/tpl-footer.component';
import { ToasterModule } from 'angular2-toaster';

@NgModule({
  declarations: [
    AppComponent,
    TplHeaderComponent,
    TplFooterComponent
  ],
  imports: [
    BrowserModule,
    ToasterModule.forRoot(),
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
