export interface CommandeRequest {
  montant: number;
  date: Date;
  clientId: number;
  details: DetailsCommande[];
}

export interface CommandeResponse {
  id: number;
  montant: number;
  date: Date;
  client: string;
}

export interface DetailsCommande {
  qteVendu: number;
  prixVente: number;
  articleId: number;
}

export interface ClientCommande {
  id: number;
  name: string;
  telephone: string;
  commandes: CommandeResponse[];
}
