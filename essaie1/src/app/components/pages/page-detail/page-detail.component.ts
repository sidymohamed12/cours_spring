import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CatalogueService } from '../../../shared/service/implement/catalogue.service';
import { ProduitDetail } from '../../../shared/model/Catalogue';
import { ProductItemComponent } from '../../catalogue/product-item/product-item.component';
import { PanierService } from '../../../shared/service/implement/panier.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-page-detail',
  imports: [ProductItemComponent, NgFor],
  templateUrl: './page-detail.component.html',
  styleUrl: './page-detail.component.css',
})
export class PageDetailComponent implements OnInit {
  // ---------------------------------------- variable ------------------------------------
  private readonly route: ActivatedRoute = inject(ActivatedRoute);
  private readonly catalogueService: CatalogueService =
    inject(CatalogueService);
  private readonly panierService: PanierService = inject(PanierService);

  produitDetail!: ProduitDetail;
  errorMessage: string = '';
  qteCom: number = 1;
  quantity = 1;
  minValue = 1;
  maxValue = 50;
  // -------------------------------------------------------------
  ngOnInit(): void {
    let id = this.route.snapshot.params['produit_id'];
    alert(id);
    this.catalogueService.getArticleById(id).subscribe((data) => {
      this.produitDetail = data;
      console.log(this.produitDetail);
    });
  }

  // Fonction d'ajout au panier
  onAddToPanier() {
    if (!this.isQuantityValid()) {
      alert(this.errorMessage);
      return;
    }

    this.produitDetail.qteCom = this.quantity;
    this.panierService.addProduit2(this.produitDetail);
    console.log(this.panierService.panierFinal().produits);
  }

  // Validation de la quantité
  isQuantityValid(): boolean {
    if (this.quantity < this.minValue) {
      this.errorMessage = `La quantité ne peut pas être inférieure à ${this.minValue}.`;
      return false;
    }
    if (this.quantity > this.produitDetail.qteStock) {
      this.errorMessage = `Stock insuffisant ! Maximum disponible : ${this.produitDetail.qteStock}.`;
      return false;
    }
    this.errorMessage = '';
    return true;
  }

  increment() {
    this.quantity++;
    this.isQuantityValid();
  }

  decrement() {
    if (this.quantity > this.minValue) {
      this.quantity--;
    }
    this.isQuantityValid();
  }

  onQuantityChange(event: Event) {
    const inputValue = (event.target as HTMLInputElement).value.trim();

    // Vérifie si la valeur contient des lettres ou des caractères non numériques
    if (!/^\d+$/.test(inputValue)) {
      this.errorMessage = 'Veuillez entrer uniquement des chiffres.';
      return;
    }
    const numericValue = parseInt(inputValue, 10);

    if (isNaN(numericValue)) {
      this.errorMessage = 'Veuillez entrer un nombre valide.';
    } else {
      this.quantity = numericValue;
    }
    this.isQuantityValid();
  }
}
