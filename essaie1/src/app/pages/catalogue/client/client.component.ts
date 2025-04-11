import { Component } from '@angular/core';
import { AuthentificationMockService } from '../../../shared/service/implement/authentification-mock.service';

@Component({
  selector: 'app-client',
  imports: [],
  templateUrl: './client.component.html',
  styleUrl: './client.component.css',
})
export class ClientComponent {
  constructor(
    public readonly authentificationService: AuthentificationMockService
  ) {}
}
