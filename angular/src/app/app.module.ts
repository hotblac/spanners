import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { DisplaySpannersComponent } from './display-spanners/display-spanners.component';
import {Routes, RouterModule} from "@angular/router";

const appRoutes: Routes = [
  {
    path: 'display-spanners',
    component: DisplaySpannersComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    DisplaySpannersComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
