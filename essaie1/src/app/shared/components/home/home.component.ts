// home.component.ts
import { Component } from '@angular/core';
import { NavComponent } from '../nav/nav.component';
import { FooterComponent } from '../footer/footer.component';
import { RouterLink } from '@angular/router';
import { BlurTextComponent } from '../blur-text/blur-text.component';
import { NewsLetterComponent } from '../news-letter/news-letter.component';

@Component({
  selector: 'app-home',
  imports: [
    NavComponent,
    FooterComponent,
    RouterLink,
    BlurTextComponent,
    NewsLetterComponent,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  public name: string = "Bio'Teranga";
}
