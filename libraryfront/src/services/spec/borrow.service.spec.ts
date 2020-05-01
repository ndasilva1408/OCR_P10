import { TestBed } from '@angular/core/testing';

import { BilletService } from '../billet.service';

describe('BorrowService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BilletService = TestBed.get(BilletService);
    expect(service).toBeTruthy();
  });
});
