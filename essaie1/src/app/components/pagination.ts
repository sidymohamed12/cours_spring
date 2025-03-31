import { ActivatedRoute, Router } from '@angular/router';

export abstract class Pagination<T> {
  items: T[] = [];
  currentPage: number = 0;
  totalPages: number = 0;
  pageSize: number = 4;

  constructor(
    protected route: ActivatedRoute,
    private readonly router: Router
  ) {}

  abstract fetchData(
    page: number,
    pageSize: number
  ): Promise<{ results: T[]; totalPages: number }>;

  // 🔹 Charger les items pour la page actuelle
  async loadItems(): Promise<void> {
    try {
      const data = await this.fetchData(this.currentPage, this.pageSize);
      console.log('Données reçues : ', data);
      this.items = data.results;
      this.totalPages = data.totalPages;
    } catch (error) {
      console.error('Erreur:', error);
    }
  }

  // page précédente
  previousPage(): void {
    if (this.currentPage > 0) {
      this.goToPage(this.currentPage - 1);
    }
  }

  // page suivante
  nextPage(): void {
    if (this.currentPage < this.totalPages - 1) {
      this.goToPage(this.currentPage + 1);
    }
  }

  // page spécifique
  goToPage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.currentPage = page;
      // Mettre à jour l'URL avec la nouvelle page
      this.router.navigate([], {
        relativeTo: this.route,
        queryParams: { page: this.currentPage },
        queryParamsHandling: 'merge', // Conserver les autres query params
      });
      this.loadItems();
    }
  }

  // tableau de numéros de page
  getPages(): number[] {
    return Array.from({ length: this.totalPages }, (_, i) => i);
  }
}
