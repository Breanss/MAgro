import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AdminComponent } from './Website/admin/admin.component';
import { UserComponent } from './Website/user/user.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { LoginComponent } from './Website/login/login.component';
import { HeaderComponent } from './Website/header/header.component';
import { ForbiddenComponent } from './Website/forbidden/forbidden.component';
import { HomeComponent } from './Website/home/home.component';
import { AppRoutingModule } from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import {AuthGuard} from "./_auth/auth.guard";
import {UserService} from "./_services/user.service";
import {AuthInterceptor} from "./_auth/auth.interceptor";
import { RegisterComponent } from './Website/register/register.component';
import { HomeappComponent } from './Application/HomeCard/homeapp/homeapp.component';
import { HeaderappComponent } from './Application/Shared/headerapp/headerapp.component';
import { FieldsComponent } from './Application/FieldsCard/fields/fields.component';
import { MapComponent } from './Application/FieldsCard/map/map.component';
import { MatTableModule } from '@angular/material/table'
import {MatIconModule} from '@angular/material/icon';
import { DialogeditComponent } from './Application/FieldsCard/dialogedit/dialogedit.component';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddfieldComponent } from './Application/FieldsCard/addfield/addfield.component';
import { CropsComponent } from './Application/CropsCard/crops/crops.component';
import { TreatmentsComponent } from './Application/TreatmentsCard/treatments/treatments.component';
import { FinancesComponent } from './Application/FinancesCard/finances/finances.component';
import { YieldComponent } from './Application/YieldCard/yield/yield.component';


@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    UserComponent,
    LoginComponent,
    HeaderComponent,
    ForbiddenComponent,
    HomeComponent,
    RegisterComponent,
    HomeappComponent,
    HeaderappComponent,
    FieldsComponent,
    MapComponent,
    DialogeditComponent,
    AddfieldComponent,
    CropsComponent,
    TreatmentsComponent,
    FinancesComponent,
    YieldComponent,


  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule,
    MatTableModule,
    MatIconModule,
    MatDialogModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass:AuthInterceptor,
      multi:true
    },
    UserService
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
