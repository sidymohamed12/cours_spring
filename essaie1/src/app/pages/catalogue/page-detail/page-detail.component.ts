import {
  AfterViewInit,
  Component,
  inject,
  OnChanges,
  OnInit,
  SimpleChanges,
} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CatalogueService } from '../../../shared/service/implement/catalogue.service';
import { ProduitDetail } from '../../../shared/model/Catalogue';
import { PanierService } from '../../../shared/service/implement/panier.service';
import { NgFor } from '@angular/common';
import { ProductItemComponent } from '../components/product-item/product-item.component';
import { ToastrService } from 'ngx-toastr';

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
  private readonly router: Router = inject(Router);
  private readonly toastr: ToastrService = inject(ToastrService);

  produitDetail!: ProduitDetail;
  errorMessage: string = '';
  qteCom: number = 1;
  quantity = 1;
  minValue = 1;
  maxValue = 50;

  // -------------------------------------------------------------
  ngOnInit(): void {
    let id = this.route.snapshot.params['produit_id'];
    this.catalogueService.getArticleById(id).subscribe((data) => {
      this.produitDetail = data;
      console.log(this.produitDetail);
    });
  }

  // ngOnInit(): void {
  //   this.route.params.subscribe((params) => {
  //     const id = params['produit_id'];
  //     this.catalogueService.getArticleById(id).subscribe((data) => {
  //       this.produitDetail = data;
  //     });
  //   });
  // }

  // Fonction d'ajout au panier
  onAddToPanier() {
    if (!this.isQuantityValid()) {
      this.toastr.error(this.errorMessage, 'Erreur de quantité');
      return;
    }

    this.produitDetail.qteCom = this.quantity;
    const produitCatalogue = {
      ...this.produitDetail,
      category: this.produitDetail.category.name,
    };
    this.panierService.addProduit2(produitCatalogue);
    this.toastr.success(
      'Produit ajouté au panier avec succès',
      'Ajout au panier'
    );
    this.router.navigateByUrl('/catalogue/panier');
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
