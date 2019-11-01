import { Component, OnInit } from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import {ResultsService} from '../../service/results.service';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-board-results',
  templateUrl: './board-results.component.html',
  styleUrls: ['./board-results.component.css']
})
export class BoardResultsComponent implements OnInit {


  distance: Observable<number>;
  compteur: Observable<number>;
  resultat: Observable<number>;

  constructor(config: NgbCarouselConfig, private resultsService: ResultsService) {

    // customize default values of carousels used by this component tree
    config.showNavigationArrows = true;
    config.showNavigationIndicators = true;


    this.distance = resultsService.distance;
    this.compteur = resultsService.nbTour;
    this.resultat = resultsService.resultat;
  }

  ngOnInit() {
  }

}
