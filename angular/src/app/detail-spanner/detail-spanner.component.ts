import { Component, OnInit } from '@angular/core';
import {Spanner} from "../spanner";
import {ActivatedRoute} from "@angular/router";
import {SpannersService} from "../spanners.service";

@Component({
  selector: 'app-detail-spanner',
  templateUrl: './detail-spanner.component.html',
  styleUrls: ['./detail-spanner.component.css']
})
export class DetailSpannerComponent implements OnInit {

  spanner: Spanner;

  constructor(private route: ActivatedRoute,
              private spannersService: SpannersService) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      const id : number = +params['id'];
      this.spannersService.getSpanner(id).subscribe(
        spanner => this.spanner = spanner
      );
    });
  }

}
