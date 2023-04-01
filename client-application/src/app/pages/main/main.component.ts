import { Component, OnInit } from '@angular/core';
import { UniversalTableColumn } from 'src/app/components/table/column.model';
import { faArrowLeft, faArrowRight } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'fixtab-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})

export class MainComponent implements OnInit {

  arrowLeft = faArrowLeft;
  arrowRight = faArrowRight;
  isMenuHide = false;
  isMenuHideAnimation = false;

  constructor() { }

  ngOnInit() {

  }

  onMenuHide(param: boolean): void {
    this.isMenuHideAnimation = param;
    setTimeout(()=>{
      this.isMenuHide = param;
    }, param ? 200 : 100);
  }
}
