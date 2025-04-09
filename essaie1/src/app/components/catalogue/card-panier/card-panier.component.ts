import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-card-panier',
  imports: [],
  templateUrl: './card-panier.component.html',
  styleUrl: './card-panier.component.css',
})
export class CardPanierComponent {
  quantity = signal(1);
  minValue = 1;
  maxValue = 50;

  increment() {
    if (this.quantity() < this.maxValue) {
      this.quantity.update((q) => q + 1);
    }
  }

  decrement() {
    if (this.quantity() > this.minValue) {
      this.quantity.update((q) => q - 1);
    }
  }

  onQuantityChange(event: Event) {
    const value = parseInt((event.target as HTMLInputElement).value, 10);
    if (!isNaN(value) && value >= this.minValue && value <= this.maxValue) {
      this.quantity.set(value);
    }
  }
}
