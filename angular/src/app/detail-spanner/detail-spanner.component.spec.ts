/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';

import { DetailSpannerComponent } from './detail-spanner.component';
import {RouterTestingModule} from "@angular/router/testing";
import {ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs";
import {DebugElement} from "@angular/core";
import {SpannersService} from "../spanners.service";
import {Spanner} from "../spanner";

describe('DetailSpannerComponent', () => {
  let component: DetailSpannerComponent;
  let fixture: ComponentFixture<DetailSpannerComponent>;
  let compiledPage: DebugElement;

  const expectedSpannerId = 1;
  const expectedSpanner = {id: 1, name: 'Keeley', size: 14};

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailSpannerComponent ],
      imports: [RouterTestingModule],
      providers: [
        {provide: ActivatedRoute, useValue: {
          'params': Observable.from([{ 'id': expectedSpannerId }])
        }},
        {provide: SpannersService, useValue: {
          getSpanner(expectedSpannerId): Observable<Spanner> {return Observable.of(expectedSpanner);}
        }}
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailSpannerComponent);
    component = fixture.componentInstance;
    compiledPage = fixture.debugElement;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render spanner name as header', async(() => {
    expect(compiledPage.query(By.css('h4')).nativeElement.textContent).toBe(expectedSpanner.name);
  }));

  it('should render spanner details in body', async(() => {
    expect(compiledPage.queryAll(By.css('td'))[0].nativeElement.textContent).toBe(expectedSpanner.id.toString());
    expect(compiledPage.queryAll(By.css('td'))[1].nativeElement.textContent).toBe(expectedSpanner.name);
    expect(compiledPage.queryAll(By.css('td'))[2].nativeElement.textContent).toBe(expectedSpanner.size.toString());
  }));

  it('should contain link back to display-spanners', async(() => {
    expect(compiledPage.query(By.css('a')).nativeElement.href).toContain("/display-spanners");
  }));

});
