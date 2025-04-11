export interface User {
  id: number;
  nom: string;
  email: string;
  password: string;
  role: UserRole;
}

export type UserRole = 'admin' | 'client';

export interface LoginResponse {
  message: string;
  success: boolean;
  data: User | null;
}
