import { Component, OnInit, Input } from '@angular/core';
import {RunnerMode, RunModel} from '../../model/run';

@Component({
  selector: 'app-runner-item',
  templateUrl: './runner-item.component.html',
  styleUrls: ['./runner-item.component.css']
})
export class RunnerItemComponent implements OnInit {

  @Input() runner: RunModel;
  @Input() mode: RunnerMode;

  constructor() { }

  ngOnInit() {
  }

  isRead(): boolean {
    return this.mode === 'ro';
  }


  reset () {

  }


  execute () {

  }

  isWaiting() {
    return false;
  }

  isRunning() {
    return true;
  }

}
