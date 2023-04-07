import { NgModule } from '@angular/core';
import { ObjectsComponent } from './objects.component';
import { ObjectsRoutingModule } from './objects-routing.module';


@NgModule({
  imports: [
    ObjectsRoutingModule
  ],
  exports: [
    ObjectsComponent
  ],
  declarations: [ObjectsComponent],
  providers: [],
})
export class ObjectsModule { }
