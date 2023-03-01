import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./Website/home/home.component";
import {AdminComponent} from "./Website/admin/admin.component";
import {LoginComponent} from "./Website/login/login.component";
import {ForbiddenComponent} from "./Website/forbidden/forbidden.component";
import {AuthGuard} from "./_auth/auth.guard";
import {RegisterComponent} from "./Website/register/register.component";
import {HomeappComponent} from "./Application/HomeCard/homeapp/homeapp.component";
import {FieldsComponent} from "./Application/FieldsCard/fields/fields.component";
import {AddfieldComponent} from "./Application/FieldsCard/addfield/addfield.component";
import {DialogeditComponent} from "./Application/FieldsCard/dialogedit/dialogedit.component";


const routes: Routes = [
  { path: '', component:HomeComponent},
  { path: 'admin', component:AdminComponent, canActivate:[AuthGuard],data:{roles:['User']} },
  { path: 'homeapp', component:HomeappComponent, canActivate:[AuthGuard],data:{roles:['User']}},
  { path: 'field', component:FieldsComponent, canActivate:[AuthGuard],data:{roles:['User']}},
  { path: 'field/pdf', component:FieldsComponent, canActivate:[AuthGuard],data:{roles:['User']}},
  { path: 'field/addfield', component:AddfieldComponent, canActivate:[AuthGuard],data:{roles:['User']}},
  { path: 'field/addfield/uldkitems/:id', component:AddfieldComponent, canActivate:[AuthGuard],data:{roles:['User']}},
  { path: 'field/dell/:id', component:FieldsComponent, canActivate:[AuthGuard],data:{roles:['User']}},
  { path: 'field/edit/:id', component:DialogeditComponent, canActivate:[AuthGuard],data:{roles:['User']}},
  { path: 'login', component:LoginComponent },
  { path: 'register', component:RegisterComponent },
  { path: 'forbidden', component:ForbiddenComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
