import { Component } from '@angular/core';
import { AuthentificationMockService } from '../../../shared/service/implement/authentification-mock.service';
import { CommandeService } from '../../../shared/service/implement/commande.service';
import {
  ClientCommande,
  CommandeResponse,
} from '../../../shared/model/Commande';

@Component({
  selector: 'app-client',
  imports: [],
  templateUrl: './client.component.html',
  styleUrl: './client.component.css',
})
export class ClientComponent {
  constructor(
    public readonly authentificationService: AuthentificationMockService,
    private readonly commandeService: CommandeService
  ) {}

  public clientCommandes!: CommandeResponse[];

  ngOnInit(): void {
    this.commandeService.getCommandeClient().subscribe(
      (commande) => {
        this.clientCommandes = commande.commandes;
        console.log('Commandes récupérées avec succès', commande);
      },
      (error) => {
        console.error('Erreur lors de la récupération des commandes', error);
      }
    );
  }
}
