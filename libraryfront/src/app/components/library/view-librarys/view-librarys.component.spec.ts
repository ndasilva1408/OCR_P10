import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewLibrarysComponent } from './view-librarys.component';

describe('ViewLibrarysComponent', () => {
  let component: ViewLibrarysComponent;
  let fixture: ComponentFixture<ViewLibrarysComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewLibrarysComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewLibrarysComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
