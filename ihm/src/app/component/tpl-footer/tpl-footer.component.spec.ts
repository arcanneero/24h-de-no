import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TplFooterComponent } from './tpl-footer.component';

describe('TplFooterComponent', () => {
  let component: TplFooterComponent;
  let fixture: ComponentFixture<TplFooterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TplFooterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TplFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
