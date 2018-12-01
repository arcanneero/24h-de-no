import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { AdminComponent } from './pages/admin/admin.component';
import { BoardComponent } from './pages/board/board.component';
import { TplHeaderComponent } from './component/tpl-header/tpl-header.component';
import { TplFooterComponent } from './component/tpl-footer/tpl-footer.component';
import { ToasterModule } from 'angular2-toaster';
import {AdminRaceComponent} from './component/admin-race/admin-race.component';
import {AdminResultsComponent} from './component/admin-results/admin-results.component';
import { RunnerItemComponent } from './component/runner-item/runner-item.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    BoardComponent,
    TplHeaderComponent,
    TplFooterComponent,
    BoardComponent,
    AdminRaceComponent,
    AdminResultsComponent,
    RunnerItemComponent
  ],
  imports: [
    BrowserModule,
    ToasterModule.forRoot(),
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
