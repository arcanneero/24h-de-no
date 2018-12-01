import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TplHeaderComponent } from './tpl-header.component';

describe('TplHeaderComponent', () => {
  let component: TplHeaderComponent;
  let fixture: ComponentFixture<TplHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TplHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TplHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
