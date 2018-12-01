
export class ResultsModel {

  results: number;
  nbTour: number;
  distance: number;


  constructor() {
    this.results = 0;
    this.distance = 0;
    this.nbTour = 0;
  }

  static of(data: any): ResultsModel {

    const results = new ResultsModel();

    results.distance = data.distance || 0;
    results.nbTour = data.nbTour || 0;
    results.results = data.cagnotte || 0;

    return results;

  }
}
