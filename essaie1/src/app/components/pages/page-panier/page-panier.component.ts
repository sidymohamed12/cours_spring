import { Component, inject, signal } from '@angular/core';
import { CardPanierComponent } from '../../catalogue/card-panier/card-panier.component';
import { PanierService } from '../../../shared/service/implement/panier.service';

@Component({
  selector: 'app-page-panier',
  imports: [],
  templateUrl: './page-panier.component.html',
  styleUrl: './page-panier.component.css',
})
export class PagePanierComponent {
  public readonly panierService: PanierService = inject(PanierService);
}
