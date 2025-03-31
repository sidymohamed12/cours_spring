import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProduitDetail, ProduitCatalogue } from '../../model/Catalogue';
import { ICatalogueService } from '../ICatalogueService';

@Injectable({
  providedIn: 'root',
})
export class CatalogueService implements ICatalogueService {
  private readonly apiUrl = 'http://localhost:8080/article';
  constructor(private readonly http: HttpClient) {}

  // récupérer tous les articles du catalogue
  getProduitCatalogue(): Observable<ProduitCatalogue[]> {
    return this.http.get<ProduitCatalogue[]>(this.apiUrl + '/catalogue');
  }

  // Récupérer tous les articles
  getArticles(page: number = 0, size: number = 4): Observable<any> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get(this.apiUrl, { params });
  }

  // Récupérer un article par son id
  getArticleById(id: number): Observable<ProduitDetail> {
    return this.http.get<ProduitDetail>(this.apiUrl + '/catalogue/' + id);
  }

  // Ajouter un article
  addArticle(article: any): Observable<any> {
    return this.http.post(`${this.apiUrl}`, article);
  }
}
