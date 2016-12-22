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
  }

  const expectedSpanners: Spanner[] = [
    {id: 0, name: 'Bertha', size: 12},
    {id: 1, name: 'Keeley', size: 14},
    {id: 2, name: 'Georgie', size: 15}
  ];

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

    mockBackend.connections.subscribe(
      (connection: MockConnection) => {
        connection.mockRespond(new Response(
          new ResponseOptions({
            body: getSpannersResponse
          })
        ));
      }
    );

    // TODO expect(service.getSpanners()).toBe(expectedSpanners);
  }));

  it('should return all spanners', inject([SpannersService], (service: SpannersService) => {
    service.getSpanners().subscribe((spanners: Spanner[]) => {
      expect(spanners).toBe(service.SPANNERS);
    });
  }));

  it('should return a spanner by id', inject([SpannersService], (service: SpannersService) => {
    expect(service.getSpanner(spannerId)).toBe(service.SPANNERS[spannerId]);
  }));
});
