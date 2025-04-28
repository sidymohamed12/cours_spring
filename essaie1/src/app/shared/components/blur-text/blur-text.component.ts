// blur-text.component.ts
import {
  Component,
  Input,
  ElementRef,
  OnInit,
  OnDestroy,
  AfterViewInit,
  ViewChild,
  ViewChildren,
  QueryList,
} from '@angular/core';
import {
  animate,
  AnimationBuilder,
  AnimationPlayer,
  style,
} from '@angular/animations';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-blur-text',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './blur-text.component.html',
  styleUrl: './blur-text.component.css',
})
export class BlurTextComponent implements OnInit, AfterViewInit, OnDestroy {
  @Input() text: string = '';
  @Input() delay: number = 200;
  @Input() className: string = '';
  @Input() animateBy: 'words' | 'letters' = 'words';
  @Input() direction: 'top' | 'bottom' = 'top';
  @Input() threshold: number = 0.1;
  @Input() rootMargin: string = '0px';
  @Input() animationFrom: Record<string, any> | null = null;
  @Input() animationTo: Record<string, any>[] | null = null;
  @Input() easing: string = 'cubic-bezier(0.215, 0.610, 0.355, 1)';
  @Input() onAnimationComplete: () => void = () => {};

  elements: string[] = [];
  private animatedCount = 0;
  private observer: IntersectionObserver | null = null;
  private readonly animationPlayers: AnimationPlayer[] = [];
  private inView = false;

  @ViewChild('textContainer') textContainer!: ElementRef;
  @ViewChildren('animatedSpans') animatedSpans!: QueryList<ElementRef>;

  constructor(private animationBuilder: AnimationBuilder) {}

  ngOnInit() {
    this.elements =
      this.animateBy === 'words' ? this.text.split(' ') : this.text.split('');
  }

  ngAfterViewInit() {
    this.setupIntersectionObserver();
  }

  ngOnDestroy() {
    if (this.observer) {
      this.observer.disconnect();
    }
    this.animationPlayers.forEach((player) => player.destroy());
  }

  private setupIntersectionObserver() {
    this.observer = new IntersectionObserver(
      ([entry]) => {
        if (entry.isIntersecting) {
          this.inView = true;
          this.startAnimations();
          if (this.textContainer?.nativeElement) {
            this.observer?.unobserve(this.textContainer.nativeElement);
          }
        }
      },
      { threshold: this.threshold, rootMargin: this.rootMargin }
    );

    if (this.textContainer?.nativeElement) {
      this.observer.observe(this.textContainer.nativeElement);
    }
  }

  private startAnimations() {
    const defaultFrom: Record<string, any> =
      this.direction === 'top'
        ? {
            filter: 'blur(10px)',
            opacity: 0,
            transform: 'translate3d(0,-50px,0)',
          }
        : {
            filter: 'blur(10px)',
            opacity: 0,
            transform: 'translate3d(0,50px,0)',
          };

    const defaultTo: Record<string, any>[] = [
      {
        filter: 'blur(5px)',
        opacity: 0.5,
        transform:
          this.direction === 'top'
            ? 'translate3d(0,5px,0)'
            : 'translate3d(0,-5px,0)',
      },
      { filter: 'blur(0px)', opacity: 1, transform: 'translate3d(0,0,0)' },
    ];

    const from = this.animationFrom || defaultFrom;
    const toSteps = this.animationTo || defaultTo;

    this.animatedSpans.forEach((span, i) => {
      setTimeout(() => {
        this.createAnimationSequence(span.nativeElement, from, toSteps, i);
      }, i * this.delay);
    });
  }

  private createAnimationSequence(
    element: HTMLElement,
    from: Record<string, any>,
    toSteps: Record<string, any>[],
    index: number
  ) {
    let currentStep = 0;

    const playNextStep = () => {
      if (currentStep >= toSteps.length) {
        this.animatedCount++;
        if (
          this.animatedCount === this.elements.length &&
          this.onAnimationComplete
        ) {
          this.onAnimationComplete();
        }
        return;
      }

      const step = toSteps[currentStep];
      const animationFactory = this.animationBuilder.build([
        style(from),
        animate(`300ms ${this.easing}`, style(step)),
      ]);

      const player = animationFactory.create(element);
      this.animationPlayers.push(player);

      player.onDone(() => {
        currentStep++;
        from = step;
        playNextStep();
      });

      player.play();
    };

    playNextStep();
  }
}
