import { Component, inject, OnInit } from '@angular/core';
import { ProductItemComponent } from '../../catalogue/product-item/product-item.component';
import { CatalogueService } from '../../../shared/service/implement/catalogue.service';
import { ProduitCatalogue } from '../../../shared/model/Catalogue';

@Component({
  selector: 'app-page-catalogue',
  imports: [ProductItemComponent],
  templateUrl: './page-catalogue.component.html',
  styleUrl: './page-catalogue.component.css',
})
export class PageCatalogueComponent implements OnInit {
  private readonly catalogueService: CatalogueService =
    inject(CatalogueService);

  produits: ProduitCatalogue[] = [];

  ngOnInit(): void {
    this.catalogueService
      .getProduitCatalogue()
      .subscribe((data: ProduitCatalogue[]) => {
        this.produits = data;
        console.log(this.produits);
      });
  }

  // constructor(route: ActivatedRoute, router: Router) {
  //   super(route, router);
  // }

  // override fetchData(
  //   page: number,
  //   pageSize: number
  // ): Promise<{ results: any[]; totalPages: number }> {
  //   return firstValueFrom(this.catalogueService.getArticles(page, pageSize));
  // }

  // async ngOnInit(): Promise<void> {
  //   this.route.queryParams.subscribe((params) => {
  //     this.currentPage = +params['page'] || 0; // Récupérer la page depuis l'URL
  //     this.loadItems();
  //   });
  // }
}
