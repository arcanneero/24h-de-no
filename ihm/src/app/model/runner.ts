
export type RunnerMode = '' | 'ro' | 'rw';
export type RunnerState = '' | 'waiting' | 'running';

export class RunnerModel {

  pilot: string;
  lane: number;
  last: Date;


  constructor(pilot: string, lane: number) {
    this.pilot = pilot;
    this.lane = lane;
  }

}
