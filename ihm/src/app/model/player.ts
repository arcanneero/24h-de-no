
export class PlayerModel {

  name: string;
  nbTour: number;
  distance: number;


  constructor(name: string) {
    this.name = name;
    this.distance = 0;
    this.nbTour = 0;
  }

  static of(data: any) {

    const results = new PlayerModel(data.nom || 'Anonyme');

    results.distance = data.distance || 0;
    results.nbTour = data.nbTour || 0;

    return results;
  }

  static ofs(data: any) {
    return data.map(item => PlayerModel.of(item));
  }
}
