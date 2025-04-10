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
  category: string;
  photos: Photo[];
}

export interface Photo {
  id: number;
  articleLibelle: string;
  imagePath: string;
}

export interface Categorie {
  id: number;
  name: string;
  code: string;
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
  category: Categorie;
  produitCategory: ProduitCatalogue[];
  photos: Photo[];

  // category: Category;
}

export interface Panier {
  produits: ProduitCatalogue[];
  date: Date;
  total: number;
}
