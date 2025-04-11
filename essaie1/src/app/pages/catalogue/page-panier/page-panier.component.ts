import { Component, inject } from '@angular/core';
import { PanierService } from '../../../shared/service/implement/panier.service';

@Component({
  selector: 'app-page-panier',
  imports: [],
  templateUrl: './page-panier.component.html',
  styleUrl: './page-panier.component.css',
})
export class PagePanierComponent {
  public readonly panierService: PanierService = inject(PanierService);
  public produitsPanier = this.panierService.panierFinal().produits;

  public deleteItem(idItem: number) {
    this.panierService.removeProduit(idItem);
  }
}
