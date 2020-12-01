import { Injectable } from '@angular/core';
import { from, Observable } from 'rxjs';
import { User } from '../models/user';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class UserServiceService {

  
  private currentUser: User;

  constructor( private http: HttpClient, private router: Router ) { }

  public getUser(): User {
    return this.currentUser;
  }

  public setUser(user: User): void {
    this.currentUser = user;
  }

  
  public async registerUser(user: string, passw: string, firstName: string, lastName: string, email: string) {
  
    return this.http.post<User>('http://localhost:8080/employees/signup', {username: user,   password: passw});
  }
  

  public async login(user: string, passw: string ): Promise<User> {

    const response: Promise<User> = this.http.post<User>('http://localhost:8080/employees/login', {username: user, password: passw }, { withCredentials: true}).toPromise();

    this.setUser( await response);

    return response;
  
  }

  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>('http://localhost:8080/employees');
  }
  public async logout(): Promise<void> {

  }
  
  public updateUser(value: String): Observable<User> {
    return this.http.put<User>('http://localhost:8080/employees/update', value);
  }
  
  public deleteUser(value: User): Observable<User> {
    return this.http.post<User>('http://localhost:8080/employees/delete', value);
  }

  

}
