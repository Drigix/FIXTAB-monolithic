import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'fixtab-search-dropdown',
  templateUrl: './serach-dropdown.component.html'
})
export class SearchDropdownComponent implements OnInit {

  @Input() options: any[] = [];
  @Input() selectedOption: any | null = null;
  @Input() placeholder: string = '';
  @Input() displayName: string = '';

  @Output() emitSelectedOption = new EventEmitter<any>();

  constructor() { }

  ngOnInit() { }

  onOptionChange(): void {
    this.emitSelectedOption.emit(this.selectedOption);
  }
}
