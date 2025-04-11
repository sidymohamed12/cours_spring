import { Routes } from '@angular/router';
import { CategorieComponent } from './components/categorie/categorie.component';
import { CommandeComponent } from './components/commande/commande.component';
import { CatalogueComponent } from './pages/catalogue/catalogue.component';
import { PageDetailComponent } from './pages/catalogue/page-detail/page-detail.component';
import { PagePanierComponent } from './pages/catalogue/page-panier/page-panier.component';
import { PageCatalogueComponent } from './pages/catalogue/page-catalogue/page-catalogue.component';
import { PageConnexionComponent } from './pages/security/page-connexion/page-connexion.component';
import { PageNofoundComponent } from './pages/page-nofound/page-nofound.component';
import { SecurityComponent } from './pages/security/security.component';
import { AdminComponent } from './pages/admin/admin.component';
import { ProduitComponent } from './pages/admin/produit/produit.component';
import { ClientComponent } from './pages/admin/client/client.component';

export const routes: Routes = [
  {
    path: 'catalogue',
    component: CatalogueComponent,
    children: [
      { path: '', component: PageCatalogueComponent },
      { path: 'panier', component: PagePanierComponent },
      { path: 'detail/:produit_id', component: PageDetailComponent },
    ],
  },
  {
    path: 'security',
    component: SecurityComponent,
    children: [{ path: 'login', component: PageConnexionComponent }],
  },
  {
    path: 'admin',
    component: AdminComponent,
    children: [
      { path: 'produit', component: ProduitComponent },
      { path: 'Commande', component: CommandeComponent },
      { path: 'client', component: ClientComponent },
    ],
  },
  { path: 'categorie', component: CategorieComponent },

  { path: '', redirectTo: '/catalogue', pathMatch: 'full' },
  { path: '**', component: PageNofoundComponent },
];
