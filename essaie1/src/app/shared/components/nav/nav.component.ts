import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { PanierService } from '../../service/implement/panier.service';

@Component({
  selector: 'app-nav',
  imports: [RouterLink],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css',
})
export class NavComponent {
  public panierService: PanierService = inject(PanierService);

  public readonly links: Link[] = [
    { name: 'catalogue', path: '/catalogue', class: '' },
    { name: 'article', path: '/article', class: '' },
    { name: 'categorie', path: '/categorie', class: '' },
    { name: 'client', path: '/client', class: '' },
    { name: 'commande', path: '/commande', class: '' },
  ];
}

interface Link {
  name: string;
  path: string;
  class: string;
  subLinks?: Array<Link>;
}
