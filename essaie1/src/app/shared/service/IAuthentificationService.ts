import { Observable } from 'rxjs';
import { LoginResponse, User } from '../model/User';

export interface IAuthentificationService {
  login(email: string, password: string): Observable<LoginResponse>;
  logout(): void;
  isAuthenticated(): boolean;
}
