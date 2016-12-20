import { Component, OnInit } from '@angular/core';
import {Spanner} from "../spanner";
import {SPANNERS} from "../mock-spanners";

@Component({
  selector: 'app-display-spanners',
  templateUrl: './display-spanners.component.html',
  styleUrls: ['./display-spanners.component.css']
})
export class DisplaySpannersComponent implements OnInit {

  spanners: Spanner[] = SPANNERS;

  constructor() { }

  ngOnInit() {
  }

}
