import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { PanierService } from '../../service/implement/panier.service';
import { AuthentificationMockService } from '../../service/implement/authentification-mock.service';

@Component({
  selector: 'app-nav',
  imports: [RouterLink],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css',
})
export class NavComponent implements OnInit {
  public panierService: PanierService = inject(PanierService);
  public readonly authentificationService: AuthentificationMockService = inject(
    AuthentificationMockService
  );
  public readonly router: Router = inject(Router);

  private isClient: boolean = false;

  public links: Link[] = [];

  ngOnInit(): void {
    this.isClient = this.authentificationService.hasRole('client');
    this.links = [
      { name: 'Accueil', path: '/home', class: '', isVisible: true },
      { name: 'Catalogue', path: '/catalogue', class: '', isVisible: true },
      {
        name: 'Commande',
        path: '/catalogue/commande',
        class: '',
        isVisible: this.isClient,
      },
      { name: 'Blog', path: '/blog', class: '', isVisible: true },
      { name: 'A Propos', path: '/about', class: '', isVisible: true },
      { name: 'Contact', path: '/contact', class: '', isVisible: true },
    ];
  }

  onLogout() {
    this.authentificationService.logout();
    this.panierService.clearPanier();
    this.isClient = false;
    this.router.navigateByUrl('/catalogue');
  }
}

interface Link {
  name: string;
  path: string;
  class: string;
  subLinks?: Array<Link>;
  isVisible?: boolean;
}
