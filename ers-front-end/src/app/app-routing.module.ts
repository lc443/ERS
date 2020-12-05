import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './employees/login/login.component';
import { RegisterComponent } from './employees/register/register.component';
import { HomepageComponent } from './home/homepage/homepage.component';

const routes: Routes = [
{ path: '', redirectTo: '/home', pathMatch: 'full'},
{ path: 'home', component: HomepageComponent },
{ path: 'login', component: LoginComponent },
{ path: 'register', component: RegisterComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
