import {PlayerModel} from "./player";

export type RunnerMode = '' | 'ro' | 'rw';
export type RunnerState = '' | 'waiting' | 'running';

export class RunModel {

  lane: number;
  nbTour: number;
  distance: number;

  pilot: PlayerModel;


  constructor(lane: number) {
    this.lane = lane;
    this.nbTour = 0;
    this.distance = 0;
  }

  static ofs(data: any[]): RunModel[] {

    return data.map(item => RunModel.of(item));

  }

  static of(data: any): RunModel {

    const results = new RunModel(data.numero || 0);

    results.distance = data.distance || 0;
    results.nbTour = data.nbTour || 0;
    results.pilot = PlayerModel.of(data.joueur);

    return results;

  }
}
