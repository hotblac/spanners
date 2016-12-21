import { Component, OnInit } from '@angular/core';
import {Spanner} from "../spanner";
import {SPANNERS} from "../mock-spanners";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-detail-spanner',
  templateUrl: './detail-spanner.component.html',
  styleUrls: ['./detail-spanner.component.css']
})
export class DetailSpannerComponent implements OnInit {

  spanner: Spanner;

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      const id : number = +params['id'];
      this.spanner = SPANNERS[id];
    });
  }

}
