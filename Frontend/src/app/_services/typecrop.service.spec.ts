import { TestBed } from '@angular/core/testing';

import { TypecropService } from './typecrop.service';

describe('TypecropService', () => {
  let service: TypecropService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TypecropService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
