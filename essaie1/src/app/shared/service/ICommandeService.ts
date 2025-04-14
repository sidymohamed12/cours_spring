import { Observable } from 'rxjs';
import {
  ClientCommande,
  CommandeRequest,
  CommandeResponse,
} from '../model/Commande';
import { Panier } from '../model/Catalogue';

export interface ICommandeService {
  getAllCommande(): Observable<CommandeRequest[]>;
  addCommande(panier: Panier): Observable<CommandeResponse>;
  convertPanierToCommande(panier: Panier): CommandeRequest;
  getCommandeClient(): Observable<ClientCommande>;
}
