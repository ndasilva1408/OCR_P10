import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewBilletComponent } from './new-billet.component';

describe('NewBilletComponent', () => {
  let component: NewBilletComponent;
  let fixture: ComponentFixture<NewBilletComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewBilletComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewBilletComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
