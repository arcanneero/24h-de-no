import { Component, OnInit } from '@angular/core';
import {RunnerModel} from '../../model/runner';

@Component({
  selector: 'app-admin-race',
  templateUrl: './admin-race.component.html',
  styleUrls: ['./admin-race.component.css']
})
export class AdminRaceComponent implements OnInit {

  runners: RunnerModel[];

  constructor() { }

  ngOnInit() {
    this.runners = [ new RunnerModel("dej", 1),
      new RunnerModel("nuyt", 2),
      new RunnerModel("sqx", 3),
      new RunnerModel("fezfzr", 4)]
  }

  resetAll () {

  }


  stopAll () {

  }

}
