import { Injectable, signal } from '@angular/core';
import { IAuthentificationService } from '../IAuthentificationService';
import { Observable, of } from 'rxjs';
import { LoginResponse, User, UserRole } from '../../model/User';
import { Mock_User } from '../../mocks/UserMock';

@Injectable({
  providedIn: 'root',
})
export class AuthentificationMockService implements IAuthentificationService {
  currentUser = signal<User | null>(null);

  constructor() {}
  hasRole(role: UserRole): boolean {
    return this.isAuthenticated() && this.currentUser()?.role === role;
  }

  login(email: string, password: string): Observable<LoginResponse> {
    const userConnect = Mock_User.find(
      (user: User) => user.email === email && user.password === password
    );
    if (userConnect) {
      this.currentUser.set(userConnect);
      return of({
        message: 'Login successful',
        success: true,
        data: userConnect,
      });
    }
    return of({
      message: 'Login failed ✖️',
      success: false,
      data: null,
    });
  }
  logout(): void {
    this.currentUser.set(null);
  }
  isAuthenticated(): boolean {
    return this.currentUser() !== null;
  }
}
