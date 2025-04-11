import { Component } from '@angular/core';
import { NavComponent } from '../../shared/components/nav/nav.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-catalogue',
  imports: [NavComponent, RouterOutlet],
  templateUrl: './catalogue.component.html',
  styleUrl: './catalogue.component.css',
})
export class CatalogueComponent {}
