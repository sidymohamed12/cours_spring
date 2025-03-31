import { Observable } from 'rxjs';
import { ProduitCatalogue } from '../model/Catalogue';

export interface ICatalogueService {
  getProduitCatalogue(): Observable<ProduitCatalogue[]>;
}
