import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CatalogueService } from '../../../shared/service/implement/catalogue.service';
import {
  ProduitCatalogue,
  ProduitDetail,
} from '../../../shared/model/Catalogue';
import { ProductItemComponent } from '../../catalogue/product-item/product-item.component';
import { PanierService } from '../../../shared/service/implement/panier.service';

@Component({
  selector: 'app-page-detail',
  imports: [ProductItemComponent],
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
  // -------------------------------------------------------------
  ngOnInit(): void {
    let id = this.route.snapshot.params['produit_id'];
    alert(id);
    this.catalogueService.getArticleById(id).subscribe((data) => {
      this.produitDetail = data;
      console.log(this.produitDetail);
    });
  }

  onValidateQte(qte: string) {
    if (qte.trim() === '') {
      this.errorMessage = 'champ vide, Veuillez entrer une quantité valide.';
    } else if (isNaN(Number(qte))) {
      this.errorMessage = 'Veuillez entrer un nombre entier.';
    } else if (Number(qte) > this.produitDetail.qteStock) {
      this.errorMessage = 'qte grande !.';
    } else {
      this.errorMessage = '';
      this.qteCom = Number(qte);
    }
  }

  onAddToPanier() {
    if (this.errorMessage === '') {
      this.produitDetail.qteCom = this.qteCom;
      this.panierService.addProduit(this.produitDetail as ProduitCatalogue);
      console.log(this.panierService.panierFinal().produits);
    } else {
      alert(this.errorMessage);
    }
  }
}
