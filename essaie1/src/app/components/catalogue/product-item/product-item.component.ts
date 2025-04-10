import { Component, inject, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ProduitCatalogue } from '../../../shared/model/Catalogue';
import { PanierService } from '../../../shared/service/implement/panier.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-product-item',
  imports: [NgFor],
  templateUrl: './product-item.component.html',
  styleUrl: './product-item.component.css',
})
export class ProductItemComponent {
  private readonly panierService = inject(PanierService);

  @Input({ required: true }) produit!: ProduitCatalogue;

  constructor(private readonly router: Router) {}

  onLoadViewDetail(id: number) {
    this.router.navigateByUrl(`/detail/${id}`); // c'est du event binding
  }

  onAddToPanier() {
    this.produit.qteCom = 1;
    this.panierService.addProduit2(this.produit);
    console.log(this.panierService.panierFinal().produits);
  }
}

// ! signifie que l'attribut est doit attendre une valeur (c'est comme promise), c'est sur que la valeur sera là
// required: true signifie que l'attribut est obligatoire
