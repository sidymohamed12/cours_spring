export interface ProduitCatalogue {
  id: number;
  libelle: string;
  prix: number;
  newPrix: number;
  qteStock: number;
  qteCom?: number;
  note: number;
  image: string;
  isdispo: boolean;
  promo: boolean;
  description: string;
}

export interface ProduitDetail {
  id: number;
  libelle: string;
  prix: number;
  newPrix: number;
  qteStock: number;
  qteCom?: number;
  note: number;
  image: string;
  isdispo: boolean;
  promo: boolean;
  description: string;
  produitCategory: ProduitCatalogue[];

  // category: Category;
}

export interface Panier {
  produits: ProduitCatalogue[];
  date: Date;
  total: number;
}
