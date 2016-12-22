import { Injectable } from '@angular/core';
import {Spanner} from "./spanner";
import {Http} from "@angular/http";
import {Observable} from "rxjs";

@Injectable()
export class SpannersService {

  restEndpoint = 'http://localhost:8090/spanners';

  SPANNERS: Spanner[] = [
    {
      id: 0,
      name: 'BerthaXX',
      size: 192
    },
    {
      id: 1,
      name: 'KeeleXy',
      size: 194
    },
    {
      id: 2,
      name: 'GeoXXXrgie',
      size: 195
    }
  ];

  constructor(private http: Http) { }

  getSpanners(): Observable<Spanner[]> {
    return Observable.of(this.SPANNERS);
  }

  getSpanner(id: number): Spanner {
    return this.SPANNERS[id];
  }

}
