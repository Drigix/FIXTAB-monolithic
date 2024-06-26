import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'fixtab-table-buttons',
  templateUrl: './table-buttons.component.html'
})

export class TableButtonsComponent implements OnInit {

  @Output() addActionEmit = new EventEmitter<boolean>();
  @Output() editActionEmit = new EventEmitter<boolean>();
  @Output() deleteActionEmit = new EventEmitter<boolean>();
  @Output() showActivityDetails = new EventEmitter<boolean>();
  @Output() changeActivityResult = new EventEmitter<boolean>();

  @Input() template = 'normal';
  @Input() showAddButton = true;
  @Input() showEditButton = true;
  @Input() showDeleteButton = true;
  @Input() isSelectedItem = false;
  @Input() isEndResult = false;


  constructor() { }

  ngOnInit() { }

  onAddAction(): void {
    this.addActionEmit.emit(false);
  }

  onEditAction(): void {
    this.editActionEmit.emit(true);
  }

  onDeleteAction(): void {
    this.deleteActionEmit.emit(true);
  }

  onShowActivityDetails(): void {
    this.showActivityDetails.emit(true);
  }

  onChangeActivityResult(): void {
    this.changeActivityResult.emit(true);
  }
}
