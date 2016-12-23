import { Component, OnInit } from '@angular/core';
import {Spanner} from "../spanner";
import {SpannersService} from "../spanners.service";

@Component({
  selector: 'app-display-spanners',
  templateUrl: './display-spanners.component.html',
  styleUrls: ['./display-spanners.component.css']
})
export class DisplaySpannersComponent implements OnInit {

  spanners: Spanner[];

  constructor(private spannersService: SpannersService) { }

  ngOnInit() {
    this.spannersService.getSpanners().subscribe(
      spanners => this.spanners = spanners
    );
  }

}
