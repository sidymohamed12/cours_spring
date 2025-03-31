import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Pagination } from '../pagination';
import { firstValueFrom } from 'rxjs';
import { CommandeService } from '../../shared/service/implement/commande.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-commande',
  imports: [CommonModule],
  templateUrl: './commande.component.html',
  styleUrl: './commande.component.css',
})
export class CommandeComponent extends Pagination<any> implements OnInit {
  constructor(
    private readonly commandeService: CommandeService,
    route: ActivatedRoute,
    router: Router
  ) {
    super(route, router);
  }
  override fetchData(
    page: number,
    pageSize: number
  ): Promise<{ results: any[]; totalPages: number }> {
    return firstValueFrom(this.commandeService.getCommande(page, pageSize));
  }
  async ngOnInit(): Promise<void> {
    this.route.queryParams.subscribe((params) => {
      this.currentPage = +params['page'] || 0; // Récupérer la page depuis l'URL
      this.loadItems();
    });
  }
}
