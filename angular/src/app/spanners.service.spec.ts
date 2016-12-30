/* tslint:disable:no-unused-variable */

import {TestBed, inject, getTestBed} from '@angular/core/testing';
import { SpannersService } from './spanners.service';
import {Http, BaseRequestOptions, XHRBackend, Response, ResponseOptions} from "@angular/http";
import {MockBackend, MockConnection} from "@angular/http/testing";
import {Spanner} from "./spanner";

describe('SpannersService', () => {

  let mockBackend: MockBackend;

  const spannerId = 1;
  const getSpannersResponse = {
    "_embedded" : {
      "spanners" : [ {
        "id" : 1,
        "name" : "Keeley",
        "size" : 12,
        "owner" : "smith",
        "_links" : {
          "self" : {
            "href" : "http://localhost:8090/spanners/1"
          },
          "spanner" : {
            "href" : "http://localhost:8090/spanners/1"
          }
        }
      }, {
        "id" : 2,
        "name" : "Bertha",
        "size" : 14,
        "owner" : "smith",
        "_links" : {
          "self" : {
            "href" : "http://localhost:8090/spanners/2"
          },
          "spanner" : {
            "href" : "http://localhost:8090/spanners/2"
          }
        }
      } ]
    },
    "_links" : {
      "self" : {
        "href" : "http://localhost:8090/spanners"
      },
      "profile" : {
        "href" : "http://localhost:8090/profile/spanners"
      }
    },
    "page" : {
      "size" : 20,
      "totalElements" : 2,
      "totalPages" : 1,
      "number" : 0
    }
  };

  const getSpannerResponse = {
    "id" : 1,
    "name" : "Keeley",
    "size" : 12,
    "owner" : "smith",
    "_links": {
      "self": {
        "href": "http://localhost:8090/spanners/1"
      },
      "spanner": {
        "href": "http://localhost:8090/spanners/1"
      }
    }
  };

  const expectedSpanners: Spanner[] = [
    {id: 1, name: 'Keeley', size: 12},
    {id: 2, name: 'Bertha', size: 14}
  ];
  const expectedSpanner: Spanner = expectedSpanners[0];

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        SpannersService,
        MockBackend,
        BaseRequestOptions,
        {
          provide: Http,
          deps: [MockBackend, BaseRequestOptions],
          useFactory:
            (backend: XHRBackend, defaultOptions: BaseRequestOptions) => {
            return new Http(backend, defaultOptions);
          }
        }
      ]
    });
    mockBackend = getTestBed().get(MockBackend);
  });

  it('should be injectable', inject([SpannersService], (service: SpannersService) => {
    expect(service).toBeTruthy();
  }));

  it('should fetch all spanners from http', inject([SpannersService], (service: SpannersService) => {

    // Mock backend will return mock getSpanners response
    mockBackend.connections.subscribe(
      (connection: MockConnection) => {
        connection.mockRespond(new Response(
          new ResponseOptions({
            body: getSpannersResponse
          })
        ));
      }
    );

    service.getSpanners().subscribe(
      response => {
        // Check response length and content against expected results
        expect(response.length).toBe(expectedSpanners.length);
        for (let el in response) {
          expect(response[el].id).toBe(expectedSpanners[el].id);
          expect(response[el].name).toBe(expectedSpanners[el].name);
          expect(response[el].size).toBe(expectedSpanners[el].size);
        }
      }
    );
  }));

  it('should fetch a single spanner from http', inject([SpannersService], (service: SpannersService) => {

    // Mock backend will return mock getSpanner response
    mockBackend.connections.subscribe(
      (connection: MockConnection) => {
        connection.mockRespond(new Response(
          new ResponseOptions({
            body: getSpannerResponse
          })
        ));
      }
    );

    service.getSpanner(spannerId).subscribe(
      response => {
        expect(response.id).toBe(expectedSpanner.id);
        expect(response.name).toBe(expectedSpanner.name);
        expect(response.size).toBe(expectedSpanner.size);
      }
    );

  }));

  it('should return a spanner by id', inject([SpannersService], (service: SpannersService) => {
    service.getSpanner(spannerId).subscribe(
      response => {
        expect(response.id).toBe(service.SPANNERS[spannerId].id);
        expect(response.name).toBe(service.SPANNERS[spannerId].name);
        expect(response.size).toBe(service.SPANNERS[spannerId].size);
      }
    );
  }));
});
