import { Component, inject } from '@angular/core';
import { PanierService } from '../../../shared/service/implement/panier.service';
import { RouterModule } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-page-panier',
  imports: [RouterModule],
  templateUrl: './page-panier.component.html',
  styleUrl: './page-panier.component.css',
})
export class PagePanierComponent {
  public readonly panierService: PanierService = inject(PanierService);
  private readonly toastr: ToastrService = inject(ToastrService);

  public produitsPanier = this.panierService.panierFinal().produits;

  public deleteItem(idItem: number) {
    this.panierService.removeProduit(idItem);
    this.toastr.success(
      'Produit supprimé du panier avec succès',
      'Suppression'
    );
  }
}
