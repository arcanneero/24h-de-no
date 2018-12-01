import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RunnerItemComponent } from './runner-item.component';

describe('RunnerItemComponent', () => {
  let component: RunnerItemComponent;
  let fixture: ComponentFixture<RunnerItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RunnerItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RunnerItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
