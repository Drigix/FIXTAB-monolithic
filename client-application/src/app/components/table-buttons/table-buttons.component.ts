import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'fixtab-table-buttons',
  templateUrl: './table-buttons.component.html'
})

export class TableButtonsComponent implements OnInit {

  @Output() addActionEmit = new EventEmitter<void>();
  @Output() editActionEmit = new EventEmitter<void>();

  @Input() showAddButton = true;
  @Input() showEditButton = true;
  @Input() showDeleteButton = true;
  @Input() isSelectedItem = false;


  constructor() { }

  ngOnInit() { }

  onAddAction(): void {
    this.addActionEmit.emit();
  }

  onEditAction(): void {
    this.editActionEmit.emit();
  }

  onDeleteAction(): void {

  }
}
