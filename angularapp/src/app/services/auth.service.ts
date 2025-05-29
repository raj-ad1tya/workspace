import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Login } from '../models/login.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

   public apiUrl = 'http://localhost:8080';

   register(user: User): Observable<any> {
    return this.http.post(`${this.apiUrl}/api/register`, user);
   }

   login(login : Login): Observable<any> {
     return this.http.post<{ token: string, userId: number, userRole: string }>(`${this.apiUrl}/api/login`, login)
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
          // Update BehaviorSubjects with user data
          this.userRoleSubject.next(response.userRole);
          this.userIdSubject.next(response.userId);
        })
      );
   }

}
