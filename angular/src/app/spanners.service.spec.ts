/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { SpannersService } from './spanners.service';

describe('SpannersService', () => {

  const spannerId = 1;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SpannersService]
    });
  });

  it('should be injectable', inject([SpannersService], (service: SpannersService) => {
    expect(service).toBeTruthy();
  }));

  it('should return all spanners', inject([SpannersService], (service: SpannersService) => {
    expect(service.getSpanners()).toBe(service.SPANNERS);
  }));

  it('should return a spanner by id', inject([SpannersService], (service: SpannersService) => {
    expect(service.getSpanner(spannerId)).toBe(service.SPANNERS[spannerId]);
  }));
});
