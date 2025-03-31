import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavComponent } from './components/layout/nav/nav.component';

@Component({
  selector: 'app-root',
  // standalone: true, // Permet d'être autonome
  imports: [RouterOutlet, NavComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'essaie1';
}
