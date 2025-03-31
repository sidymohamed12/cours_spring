import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CatalogueService } from '../../../shared/service/implement/catalogue.service';
import { ProduitDetail } from '../../../shared/model/Catalogue';
import { ProductItemComponent } from '../../catalogue/product-item/product-item.component';

@Component({
  selector: 'app-page-detail',
  imports: [ProductItemComponent],
  templateUrl: './page-detail.component.html',
  styleUrl: './page-detail.component.css',
})
export class PageDetailComponent implements OnInit {
  private readonly route: ActivatedRoute = inject(ActivatedRoute);
  private readonly catalogueService: CatalogueService =
    inject(CatalogueService);

  produitDetail!: ProduitDetail;
  ngOnInit(): void {
    let id = this.route.snapshot.params['produit_id'];
    alert(id);
    this.catalogueService.getArticleById(id).subscribe((data) => {
      this.produitDetail = data;
      console.log(this.produitDetail);
    });
  }

  onValidateQte() {
    alert('ok');
  }
}
