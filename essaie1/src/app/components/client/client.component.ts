import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { Pagination } from '../pagination';
import { firstValueFrom } from 'rxjs';
import { ClientService } from '../../shared/service/implement/client.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-client',
  imports: [CommonModule],
  templateUrl: './client.component.html',
  styleUrl: './client.component.css',
})
export class ClientComponent extends Pagination<any> implements OnInit {
  private readonly clientService: ClientService = inject(ClientService);

  constructor(route: ActivatedRoute, router: Router) {
    super(route, router);
  }
  override fetchData(
    page: number,
    pageSize: number
  ): Promise<{ results: any[]; totalPages: number }> {
    return firstValueFrom(this.clientService.getClient(page, pageSize));
  }
  async ngOnInit(): Promise<void> {
    this.route.queryParams.subscribe((params) => {
      this.currentPage = +params['page'] || 0; // Récupérer la page depuis l'URL
      this.loadItems();
    });
  }
}
