import { Injectable, signal } from '@angular/core';
import { Panier, ProduitCatalogue } from '../../model/Catalogue';

@Injectable({
  providedIn: 'root',
})
export class PanierService {
  panierFinal = signal<Panier>(this.initialize());
  constructor() {}

  addProduit(produit: ProduitCatalogue): void {
    let prix: number = produit.promo ? produit.newPrix : produit.prix;
    let montant: number = prix * produit.qteCom!;
    this.panierFinal.update((panier) => {
      panier.produits?.push(produit);
      panier.total += montant;
      return panier;
    });
  }

  addProduit2(produit: ProduitCatalogue): void {
    let prix: number = produit.promo ? produit.newPrix : produit.prix;
    let montant: number = prix * produit.qteCom!;
    this.panierFinal.update((panier) => ({
      ...panier,
      produits: this.checkExistence(panier.produits, produit),
      total: panier.total + montant,
      date: new Date(),
    }));
  }

  checkExistence(
    produitList: ProduitCatalogue[],
    newProduit: ProduitCatalogue
  ): ProduitCatalogue[] {
    let index: number = produitList.findIndex(
      (produit) => newProduit.id === produit.id
    );
    if (index >= 0) {
      produitList[index].qteCom! += newProduit.qteCom!;
    } else {
      produitList.push(newProduit);
    }
    return produitList;
  }

  removeProduit(id: number): void {
    this.panierFinal.update((panier) => {
      let index: number = panier.produits.findIndex(
        (produit) => produit.id === id
      );
      if (index >= 0) {
        panier.total -=
          panier.produits[index].prix * panier.produits[index].qteCom!;
        panier.produits.splice(index, 1);
      }
      return panier;
    });
  }

  clearPanier(): void {
    this.initialize();
  }

  private initialize(): Panier {
    return {
      produits: [],
      date: new Date(),
      total: 0,
    };
  }
}
