import { Injectable } from '@angular/core';
import {Spanner} from "./spanner";

@Injectable()
export class SpannersService {

  SPANNERS: Spanner[] = [
    {
      id: 0,
      name: 'Bertha',
      size: 12
    },
    {
      id: 1,
      name: 'Keeley',
      size: 14
    },
    {
      id: 2,
      name: 'Georgie',
      size: 15
    }
  ];

  constructor() { }

  getSpanners(): Spanner[] {
    return this.SPANNERS;
  }

  getSpanner(id: number): Spanner {
    return this.SPANNERS[id];
  }

}
