import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminaddbusComponent } from './adminaddbus/adminaddbus.component';
import { AdminavComponent } from './components/adminav/adminav.component';
import { AdminviewbookingComponent } from './components/adminviewbooking/adminviewbooking.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminaddbusComponent,
    AdminavComponent,
    AdminviewbookingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
