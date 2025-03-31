import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  private readonly apiUrl = 'http://localhost:8080/client';
  constructor(private readonly http: HttpClient) {}

  getClient(page: number = 0, size: number = 0): Observable<any> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get(this.apiUrl, { params });
  }
}
