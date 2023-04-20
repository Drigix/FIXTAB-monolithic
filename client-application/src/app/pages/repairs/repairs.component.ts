import { Component, OnInit } from '@angular/core';
import { DialogService } from 'primeng/dynamicdialog';
import { UniversalTableColumn } from 'src/app/components/table/column.model';
import { RepairsDialogComponent } from './repairs-dialog/repairs-dialog.component';
import { RequestRepairService } from 'src/app/services/request-repair.service';
import { Request } from 'src/app/entitites/request.model';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'fixtab-repairs',
  templateUrl: './repairs.component.html',
  styleUrls: ['./repairs.component.scss']
})

export class RepairsComponent implements OnInit {

  selectedRepair: any;
  columns: UniversalTableColumn[] = [];
  requestRepairs: Request[] = [];
  repairs: any[] = [];
  openDeleteDialog = false;

  constructor(
    private dialogService: DialogService,
    private requestRepairService: RequestRepairService
  ) { }

  ngOnInit() {
    this.loadColumns();
    this.loadRepairs();
  }

  loadColumns(): void {
    this.columns = [
      {
        header: 'Nazwa',
        field: 'description'
      },
      {
        header: 'Data otwarcia',
        field: 'openDate'
      },
    ];
  }

  loadRepairs(): void {
    this.requestRepairService.getAllNotDeleted().subscribe(
      (res: HttpResponse<Request[]>) => {
        this.requestRepairs = res.body ?? [];
      }
    );
  }

  onRepairSelected(event: any): void {
    this.selectedRepair = event;
  }

  openRepairsDialog(edit: boolean): void {
    const ref = this.dialogService.open(RepairsDialogComponent, {
      header: edit ? 'Edytuj pracownika' : 'Dodaj pracownika',
      width: '60%',
      height: '70%',
      data: {
        edit: edit,
        repair: this.selectedRepair
      }
    });
    ref.onClose.subscribe((response) => this.handleRepairsDialog(response));
  }

  openRepairsDeleteDialog(event: boolean): void {
    this.openDeleteDialog = event;
  }

  handleRepairsDialog(response: any) {
    this.loadRepairs();
  }

  handleRepairsDeleteDialog(response: boolean): void {
    if(response) {
      // this.employeeService.delete(this.selectedEmployee?.employeeId!).subscribe(
      //   {
      //     next: () => {
      //       this.messageService.add({key: 'mainToast', severity: 'success', summary: 'Success',
      //         detail: 'usunięto!'});
      //       this.loadEmployees();
      //     },
      //     error: () => {
      //       this.messageService.add({key: 'mainToast', severity: 'error', summary: 'Error',
      //         detail: 'nie usunięto!'});
      //     }
      //   }
      // );
    }
    this.openDeleteDialog = false;
  }
}
