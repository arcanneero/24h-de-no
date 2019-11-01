import { Component, OnInit } from '@angular/core';
import {RunModel} from '../../model/run';
import {RunService} from '../../service/run.service';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-board-race',
  templateUrl: './board-race.component.html',
  styleUrls: ['./board-race.component.css']
})
export class BoardRaceComponent implements OnInit {

  runners: Observable<RunModel[]>;

  constructor(private runService: RunService) {

    this.runners = runService.runners;
  }

  resetAll () {

  }


  stopAll () {

  }

  ngOnInit(): void {
  }
}
