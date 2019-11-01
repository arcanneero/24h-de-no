import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardRaceComponent } from './board-race.component';

describe('BoardRaceComponent', () => {
  let component: BoardRaceComponent;
  let fixture: ComponentFixture<BoardRaceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BoardRaceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoardRaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
