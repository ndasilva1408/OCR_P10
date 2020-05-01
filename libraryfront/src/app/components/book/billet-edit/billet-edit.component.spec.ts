import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BilletEditComponent } from './billet-edit.component';

describe('BorrowEditComponent', () => {
  let component: BilletEditComponent;
  let fixture: ComponentFixture<BilletEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BilletEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BilletEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
