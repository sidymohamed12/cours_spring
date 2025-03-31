import { Component, inject, OnInit } from '@angular/core';
import { ArticleService } from '../../shared/service/article/article.service';
import { firstValueFrom } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Pagination } from '../pagination';

@Component({
  selector: 'app-article',
  imports: [],
  templateUrl: './article.component.html',
  styleUrl: './article.component.css',
})
export class ArticleComponent extends Pagination<any> implements OnInit {
  private readonly articleService: ArticleService = inject(ArticleService);
  constructor(route: ActivatedRoute, router: Router) {
    super(route, router);
  }

  async fetchData(
    page: number,
    pageSize: number
  ): Promise<{ results: any[]; totalPages: number }> {
    return firstValueFrom(this.articleService.getArticles(page, pageSize));
  }

  async ngOnInit(): Promise<void> {
    this.route.queryParams.subscribe((params) => {
      this.currentPage = +params['page'] || 0; // Récupérer la page depuis l'URL
      this.loadItems();
    });
  }
}
