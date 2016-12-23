import { Injectable } from '@angular/core';
import {Spanner} from "./spanner";
import {Http, Response} from "@angular/http";
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
    return this.http.get(this.restEndpoint)
      .map((response: Response) => <Spanner[]> response.json()._embedded.spanners);
  }

  getSpanner(id: number): Spanner {
    return this.SPANNERS[id];
  }

}
