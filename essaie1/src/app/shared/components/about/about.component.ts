import { Component } from '@angular/core';
import { NavComponent } from '../nav/nav.component';
import { FooterComponent } from '../footer/footer.component';

@Component({
  selector: 'app-about',
  imports: [NavComponent, FooterComponent],
  templateUrl: './about.component.html',
  styleUrl: './about.component.css',
})
export class AboutComponent {
  constructor() {
    // Initialisation ou logique spécifique au composant peut être ajoutée ici
  }

  public teams: TeamMember[] = [
    {
      name: 'Sidy Mohamed SAIZONOU',
      role: 'D. Technique & Digital',
      description:
        'Supervise les solutions numériques et les outils techniques.',
      imageUrl: 'img/teams/sms.jpeg',
    },
    {
      name: 'Henri Pierre BASSENE',
      role: 'D. Innovation & Développement',
      description: 'Développe les stratégies et l’innovation.',
      imageUrl: 'img/teams/hp.jpg',
    },
    {
      name: 'Dieyi DIOUF',
      role: 'D. Logistique & Approvisionnement',
      description: 'Coordonne les flux logistiques et les approvisionnements.',
      imageUrl: 'img/teams/dd.jpg',
    },
    {
      name: 'Serigne C.A.T. Cherif DIALLO',
      role: 'D. Financement & Coordination',
      description: 'Coordonne les activités et les financements.',
      imageUrl: 'img/teams/scatcd.jpg',
    },
    {
      name: 'Charles SENE',
      role: 'D. Gestion des Entrepôts & Stocks',
      description: 'Gère les entrepôts et les stocks.',
      imageUrl: 'img/teams/cs.jpg',
    },
    {
      name: 'Doudou M.E.B NDIAYE',
      role: 'D. Commercial & Partenariats B2B',
      description: 'Développe les ventes et les partenariats B2B.',
      imageUrl: 'img/teams/dmbn.jpg',
    },
    {
      name: 'Hassimiou DIA',
      role: 'D. Marketing & Communication Digitale',
      description: 'Gère le marketing et la communication digitale.',
      imageUrl: 'img/teams/hd.jpg',
    },
    {
      name: 'Amy Faye',
      role: 'D. Service Client & Expérience Utilisateur',
      description: 'Assure un service client de qualité.',
      imageUrl: 'img/teams/af.jpg',
    },
  ];
}

export interface TeamMember {
  name: string;
  role: string;
  description: string;
  imageUrl: string;
}
