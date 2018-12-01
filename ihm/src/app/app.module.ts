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
import {BoardRaceComponent} from './component/board-race/board-race.component';
import {BoardResultsComponent} from './component/board-results/board-results.component';
import {BoardHallComponent} from './component/board-hall/board-hall.component';
import {NgbModule, NgbCarouselModule} from '@ng-bootstrap/ng-bootstrap';
import {HttpClientModule} from '@angular/common/http';
import {CommonsService} from "./service/commons.service";
import {HallService} from "./service/hall.service";
import {ResultsService} from "./service/results.service";
import {RunService} from "./service/run.service";

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
    RunnerItemComponent,
    BoardRaceComponent,
    BoardResultsComponent,
    BoardHallComponent
  ],
  imports: [
    BrowserModule,
    ToasterModule.forRoot(),
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    NgbModule,
    NgbCarouselModule
  ],
  providers: [CommonsService,
    HallService,
    ResultsService,
    RunService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
