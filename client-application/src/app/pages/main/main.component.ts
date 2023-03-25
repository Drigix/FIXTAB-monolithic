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

  columns: UniversalTableColumn[] = [];

  constructor() { }

  ngOnInit() {
    this.columns = [
      {
        header: "Imie",
        name: "Name",
        field: "name"
      },
      {
        header: "Nazwisko",
        name: "Surname",
        field: "surname"
      },
    ]
  }

  onMenuHide(param: boolean): void {
    this.isMenuHideAnimation = param;
    setTimeout(()=>{
      this.isMenuHide = param;
    }, 100);
  }
}
