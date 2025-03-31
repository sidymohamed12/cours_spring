import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ArticleService {
  private readonly apiUrl = 'http://localhost:8080/article';
  constructor(private readonly http: HttpClient) {}

  // 🔹 Récupérer tous les articles
  getArticles(page: number = 0, size: number = 4): Observable<any> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get(this.apiUrl, { params });
  }

  // 🔹 Ajouter un article
  addArticle(article: any): Observable<any> {
    return this.http.post(`${this.apiUrl}`, article);
  }
}
