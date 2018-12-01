import { Component, OnInit, Input } from '@angular/core';
import {RunnerMode, RunnerModel} from '../../model/runner';

@Component({
  selector: 'app-runner-item',
  templateUrl: './runner-item.component.html',
  styleUrls: ['./runner-item.component.css']
})
export class RunnerItemComponent implements OnInit {

  @Input() runner: RunnerModel;
  @Input() mode: RunnerMode;

  constructor() { }

  ngOnInit() {
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
