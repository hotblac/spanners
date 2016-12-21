/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { SpannersService } from './spanners.service';

describe('SpannersService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SpannersService]
    });
  });

  it('should ...', inject([SpannersService], (service: SpannersService) => {
    expect(service).toBeTruthy();
  }));
});
