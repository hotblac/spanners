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

  id: number;
  spanner: Spanner;

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.spanner = SPANNERS[this.id];
  }

}
