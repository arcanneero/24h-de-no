import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardHallComponent } from './board-hall.component';

describe('BoardHallComponent', () => {
  let component: BoardHallComponent;
  let fixture: ComponentFixture<BoardHallComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BoardHallComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoardHallComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
