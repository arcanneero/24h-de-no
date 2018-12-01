import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-results',
  templateUrl: './admin-results.component.html',
  styleUrls: ['./admin-results.component.css']
})
export class AdminResultsComponent implements OnInit {

  affiche: number;
  reel: number;
  retention: number;

  resultat: number;
  cagnotte: number;

  constructor() { }

  ngOnInit() {
    this.affiche = 100;
    this.reel = 110;
    this.retention = 10;
    this.resultat = 8000;
    this.cagnotte = 10000;
  }

}
