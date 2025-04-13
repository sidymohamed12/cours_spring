export interface CommandeRequest {
  montant: number;
  date: Date;
  client: number;
  details: DetailsCommande[];
}

export interface CommandeResponse {
  id: number;
  montant: number;
  date: Date;
}

export interface DetailsCommande {
  qteVendu: number;
  prixVente: number;
  produitId: number;
}

export interface ClientCommande {
  id: number;
  name: string;
  telephone: string;
  commandes: CommandeResponse[];
}
