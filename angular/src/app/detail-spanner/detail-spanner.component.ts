import { Component, OnInit } from '@angular/core';
import {Spanner} from "../spanner";
import {SPANNERS} from "../mock-spanners";

@Component({
  selector: 'app-detail-spanner',
  templateUrl: './detail-spanner.component.html',
  styleUrls: ['./detail-spanner.component.css']
})
export class DetailSpannerComponent implements OnInit {

  spanner: Spanner = SPANNERS[0];

  constructor() { }

  ngOnInit() {
  }

}
