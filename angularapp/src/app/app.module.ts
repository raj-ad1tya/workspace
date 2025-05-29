import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminaddbusComponent } from './components/adminaddbus/adminaddbus.component';
import { AdminavComponent } from './components/adminav/adminav.component';
import { AdminviewbookingComponent } from './components/adminviewbooking/adminviewbooking.component';
import { AdminviewbusComponent } from './components/adminviewbus/adminviewbus.component';
import { AdminviewfeedbackComponent } from './components/adminviewfeedback/adminviewfeedback.component';
import { AuthguardComponent } from './components/authguard/authguard.component';
import { ErrorComponent } from './components/error/error.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { UseraddfeedbackComponent } from './components/useraddfeedback/useraddfeedback.component';
import { UserbookbusComponent } from './components/userbookbus/userbookbus.component';
import { UsernavComponent } from './components/usernav/usernav.component';
import { UserviewbookingComponent } from './components/userviewbooking/userviewbooking.component';
import { UserviewbusComponent } from './components/userviewbus/userviewbus.component';
import { UserviewfeedbackComponent } from './components/userviewfeedback/userviewfeedback.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminaddbusComponent,
    AdminavComponent,
    AdminviewbookingComponent,
    AdminviewbusComponent,
    AdminviewfeedbackComponent,
    AuthguardComponent,
    ErrorComponent,
    HomeComponent,
    LoginComponent,
    NavbarComponent,
    RegistrationComponent,
    UseraddfeedbackComponent,
    UserbookbusComponent,
    UsernavComponent,
    UserviewbookingComponent,
    UserviewbusComponent,
    UserviewfeedbackComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
