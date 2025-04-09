import { Routes } from '@angular/router';
import { ArticleComponent } from './components/article/article.component';
import { CategorieComponent } from './components/categorie/categorie.component';
import { ClientComponent } from './components/client/client.component';
import { CommandeComponent } from './components/commande/commande.component';
import { PageCatalogueComponent } from './components/pages/page-catalogue/page-catalogue.component';
import { PageDetailComponent } from './components/pages/page-detail/page-detail.component';
import { PageNofoundComponent } from './components/pages/page-nofound/page-nofound.component';
import { PagePanierComponent } from './components/pages/page-panier/page-panier.component';

export const routes: Routes = [
  { path: 'article', component: ArticleComponent },
  { path: 'categorie', component: CategorieComponent },
  { path: 'client', component: ClientComponent },
  { path: 'commande', component: CommandeComponent },
  { path: 'catalogue', component: PageCatalogueComponent },
  { path: 'panier', component: PagePanierComponent },
  { path: 'detail/:produit_id', component: PageDetailComponent },
  { path: '', redirectTo: '/catalogue', pathMatch: 'full' },
  { path: '**', component: PageNofoundComponent },
];
