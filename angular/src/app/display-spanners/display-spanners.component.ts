import { Component, OnInit } from '@angular/core';
import {Spanner} from "../spanner";

@Component({
  selector: 'app-display-spanners',
  templateUrl: './display-spanners.component.html',
  styleUrls: ['./display-spanners.component.css']
})
export class DisplaySpannersComponent implements OnInit {

  spanners: Spanner[] = [
    {
      id: 1,
      name: 'Bertha',
      size: 12
    },
    {
      id: 2,
      name: 'Keeley',
      size: 14
    },
    {
      id: 3,
      name: 'Georgie',
      size: 15
    }
  ];

  constructor() { }

  ngOnInit() {
  }

}
