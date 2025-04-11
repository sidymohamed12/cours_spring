import { Observable } from 'rxjs';
import { LoginResponse, UserRole } from '../model/User';

export interface IAuthentificationService {
  login(email: string, password: string): Observable<LoginResponse>;
  logout(): void;
  isAuthenticated(): boolean;
  hasRole(role: UserRole): boolean;
}
