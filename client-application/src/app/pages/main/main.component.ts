import { ChangeDateService } from './../../services/change-date.service';
import { Component, OnInit } from '@angular/core';
import { UniversalTableColumn } from 'src/app/components/table/column.model';
import { faArrowLeft, faArrowRight } from '@fortawesome/free-solid-svg-icons';
import { DialogService } from 'primeng/dynamicdialog';
import { ProfileComponent } from '../profile/profile.component';

@Component({
  selector: 'fixtab-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})

export class MainComponent implements OnInit {

  arrowLeft = faArrowLeft;
  arrowRight = faArrowRight;
  currentDate: string | null = null;
  currentTime: string | null = null;
  isMenuHide = false;
  isMenuHideAnimation = false;

  constructor(
    private dialogService: DialogService,
    private changeDateService: ChangeDateService
  ) { }

  ngOnInit() {
    this.currentDate = this.changeDateService.changeDateToString(new Date());
    setInterval( () => {
      this.currentTime = this.changeDateService.changeTimeToString(new Date());
    }, 1000);
  }

  onMenuHide(param: boolean): void {
    this.isMenuHideAnimation = param;
    setTimeout(()=>{
      this.isMenuHide = param;
    }, param ? 200 : 100);
  }

  onProfileClick(): void {
    const ref = this.dialogService.open(ProfileComponent, {
      header: 'Profil',
      width: '60%'
    });
  }

  onLogoff(): void {
    window.sessionStorage.setItem('accessToken', '');
    window.sessionStorage.setItem('expirationDate', '');
    window.location.reload();
  }
}
