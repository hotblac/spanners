import { Component, OnInit } from '@angular/core';
import {Spanner} from "../spanner";
import {SPANNERS} from "../mock-spanners";
import {Router} from "@angular/router";

@Component({
  selector: 'app-display-spanners',
  templateUrl: './display-spanners.component.html',
  styleUrls: ['./display-spanners.component.css']
})
export class DisplaySpannersComponent implements OnInit {

  spanners: Spanner[] = SPANNERS;

  constructor(private router: Router) { }

  ngOnInit() {
  }

  onSelect(spanner: Spanner) {
    this.router.navigate(['/detail-spanner', spanner.id]);
  }

}
