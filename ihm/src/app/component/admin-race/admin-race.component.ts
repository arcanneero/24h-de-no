import { Component, OnInit } from '@angular/core';
import {RunModel} from '../../model/run';

@Component({
  selector: 'app-admin-race',
  templateUrl: './admin-race.component.html',
  styleUrls: ['./admin-race.component.css']
})
export class AdminRaceComponent implements OnInit {

  runners: RunModel[];

  constructor() { }

  ngOnInit() {
    this.runners = [ new RunModel(1),
      new RunModel(2),
      new RunModel(3),
      new RunModel(4)]
  }

  resetAll () {

  }


  stopAll () {

  }

}
