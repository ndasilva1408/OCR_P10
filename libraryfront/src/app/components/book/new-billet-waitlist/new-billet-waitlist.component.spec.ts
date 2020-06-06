import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewBilletWaitlistComponent } from './new-billet-waitlist.component';

describe('NewBilletWaitlistComponent', () => {
  let component: NewBilletWaitlistComponent;
  let fixture: ComponentFixture<NewBilletWaitlistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewBilletWaitlistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewBilletWaitlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
