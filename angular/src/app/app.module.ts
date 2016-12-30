import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { CollapseModule } from 'ng2-bootstrap';

import { AppComponent } from './app.component';
import { DisplaySpannersComponent } from './display-spanners/display-spanners.component';
import {Routes, RouterModule} from "@angular/router";
import { SplashComponent } from './splash/splash.component';
import { DetailSpannerComponent } from './detail-spanner/detail-spanner.component';
import {SpannersService} from "./spanners.service";

const appRoutes: Routes = [
  {
    path: 'display-spanners',
    component: DisplaySpannersComponent
  },
  {
    path: 'detail-spanner/:id',
    component: DetailSpannerComponent
  },
  {
    path: '',
    component: SplashComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    DisplaySpannersComponent,
    SplashComponent,
    DetailSpannerComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    CollapseModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [SpannersService],
  bootstrap: [AppComponent]
})
export class AppModule { }
