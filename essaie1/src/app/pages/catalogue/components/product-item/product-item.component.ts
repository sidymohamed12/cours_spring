import { Component, inject, Input } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { NgFor } from '@angular/common';
import { PanierService } from '../../../../shared/service/implement/panier.service';
import { ProduitCatalogue } from '../../../../shared/model/Catalogue';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-product-item',
  imports: [NgFor],
  templateUrl: './product-item.component.html',
  styleUrl: './product-item.component.css',
})
export class ProductItemComponent {
  private readonly panierService = inject(PanierService);

  @Input({ required: true }) produit!: ProduitCatalogue;

  constructor(
    private readonly router: Router,
    private readonly toastr: ToastrService
  ) {}

  async onLoadViewDetail(id: number) {
    this.router
      .navigateByUrl('.', {
        skipLocationChange: true,
      })
      .then(() => {
        this.router.navigate([`/catalogue/detail/${id}`]);
      });
  }

  // onLoadViewDetail(id: number) {
  //   this.router.navigateByUrl(`catalogue/detail/${id}`); // c'est du event binding
  //   // setTimeout(() => {
  //   //   window.location.reload();
  //   // });
  // }

  onAddToPanier() {
    this.produit.qteCom = 1;
    this.panierService.addProduit2(this.produit);
    this.toastr.success('Produit ajouter au panier avec succés');
  }

  showSuccess() {
    this.toastr.error("Message d'erreur", 'Titre'); // Erreur
    this.toastr.warning("Message d'avertissement", 'Titre'); // Warning
    this.toastr.info("Message d'information", 'Titre'); // Info
  }
}

// ! signifie que l'attribut est doit attendre une valeur (c'est comme promise), c'est sur que la valeur sera là
// required: true signifie que l'attribut est obligatoire
