import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {PlayerModel} from "../../model/player";
import {ResultsService} from "../../service/results.service";
import {HallService} from "../../service/hall.service";

@Component({
  selector: 'app-board-hall',
  templateUrl: './board-hall.component.html',
  styleUrls: ['./board-hall.component.css']
})
export class BoardHallComponent implements OnInit {

  players: Observable<PlayerModel[]>;

  constructor(private hallService: HallService) {

    this.players = hallService.players;
  }

  ngOnInit() {
  }

  resetAll () {

  }


  stopAll () {

  }

}
