import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
  ClientCommande,
  CommandeRequest,
  CommandeResponse,
} from '../../model/Commande';
import { ICommandeService } from '../ICommandeService';
import { Panier } from '../../model/Catalogue';
import { AuthentificationMockService } from './authentification-mock.service';

@Injectable({
  providedIn: 'root',
})
export class CommandeService implements ICommandeService {
  private readonly apiUrl = 'http://localhost:8080';

  constructor(
    private readonly http: HttpClient,
    private readonly authentifactionService: AuthentificationMockService
  ) {}

  getCommandeClient(): Observable<ClientCommande> {
    return this.http.get<ClientCommande>(
      `${this.apiUrl}/client/${this.authentifactionService.currentUser()
        ?.id!}/commandes`
    );
  }

  addCommande(panier: Panier): Observable<CommandeResponse> {
    console.log('panier', JSON.stringify(this.convertPanierToCommande(panier)));
    return this.http.post<CommandeResponse>(
      this.apiUrl + '/commande',
      this.convertPanierToCommande(panier)
    );
  }

  convertPanierToCommande(panier: Panier): CommandeRequest {
    return {
      date: new Date(),
      clientId: this.authentifactionService.currentUser()?.id!,
      montant: panier.total,
      details: panier.produits.map((produit) => {
        return {
          articleId: produit.id,
          qteVendu: produit.qteCom!,
          prixVente: produit.promo ? produit.newPrix : produit.prix,
        };
      }),
    };
  }

  getAllCommande(): Observable<CommandeRequest[]> {
    return this.http.get<CommandeRequest[]>(`${this.apiUrl}/commande/all`);
  }

  getCommande(page: number = 0, size: number = 0): Observable<any> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get(`${this.apiUrl}/commande`, { params });
  }
}
