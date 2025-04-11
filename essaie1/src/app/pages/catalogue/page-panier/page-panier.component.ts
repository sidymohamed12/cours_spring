import { Component, inject } from '@angular/core';
import { PanierService } from '../../../shared/service/implement/panier.service';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-page-panier',
  imports: [RouterModule],
  templateUrl: './page-panier.component.html',
  styleUrl: './page-panier.component.css',
})
export class PagePanierComponent {
  public readonly panierService: PanierService = inject(PanierService);
  private readonly router: Router = inject(Router);

  public produitsPanier = this.panierService.panierFinal().produits;

  public deleteItem(idItem: number) {
    this.panierService.removeProduit(idItem);
  }
}
