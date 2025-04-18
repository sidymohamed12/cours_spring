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
import { ClientComponent } from './pages/catalogue/client/client.component';
import { HomeComponent } from './shared/components/home/home.component';
import { AboutComponent } from './shared/components/about/about.component';
import { BlogComponent } from './shared/components/blog/blog.component';
import { ContactComponent } from './shared/components/contact/contact.component';

export const routes: Routes = [
  {
    path: 'catalogue',
    component: CatalogueComponent,
    children: [
      { path: '', component: PageCatalogueComponent },
      { path: 'panier', component: PagePanierComponent },
      { path: 'detail/:produit_id', component: PageDetailComponent },
      { path: 'commande', component: ClientComponent },
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
    ],
  },
  { path: 'categorie', component: CategorieComponent },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'blog', component: BlogComponent },
  { path: 'contact', component: ContactComponent },

  { path: '', redirectTo: '/catalogue', pathMatch: 'full' },
  { path: '**', component: PageNofoundComponent },
];
