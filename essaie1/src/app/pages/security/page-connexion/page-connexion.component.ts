import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthentificationMockService } from '../../../shared/service/implement/authentification-mock.service';
import { LoginResponse } from '../../../shared/model/User';
import { PanierService } from '../../../shared/service/implement/panier.service';
import { CommandeService } from '../../../shared/service/implement/commande.service';

@Component({
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './page-connexion.component.html',
  styleUrl: './page-connexion.component.css',
})
export class PageConnexionComponent implements OnInit {
  // -------------------------------------------------------------------------
  // 1er méthode : FormGroup
  // formlogin: FormGroup = new FormGroup({
  //   email: new FormControl('', [Validators.required, Validators.email]),
  //   password: new FormControl('', [
  //     Validators.required,
  //     Validators.minLength(6),
  //   ]),
  // });
  // -------------------------------------------------------------------------

  public errorMessage: string = '';
  public isError: boolean = false;
  public loading: boolean = false;

  // 2ème méthode : FormBuilder
  formLogin2: FormGroup;
  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly route: ActivatedRoute,
    private readonly router: Router,
    private readonly authentificationService: AuthentificationMockService,
    private readonly panierService: PanierService,
    private readonly commandeService: CommandeService
  ) {
    this.formLogin2 = this.formBuilder.group({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
      ]),
    });
  }

  login() {
    if (this.formLogin2.invalid) {
      return;
    }
    this.loading = true;
    this.isError = false;
    const { email, password } = this.formLogin2.value;
    this.authentificationService.login(email, password).subscribe({
      next: (response: LoginResponse) => {
        this.loading = false;
        if (response?.success) {
          if (response.data?.role === 'admin') {
            alert('Admin login successful');
          } else if (response.data?.role === 'client') {
            this.route.queryParams.subscribe((params) => {
              let queryParams = params['link'];
              if (queryParams == 'panier') {
                // ajout commande et rediriger
                this.commandeService
                  .addCommande(this.panierService.panierFinal())
                  .subscribe(() => {
                    alert('Commande ajoutée avec succès');
                    this.panierService.clearPanier();
                  });
                this.router.navigateByUrl('/catalogue/commande');
              }
              this.router.navigateByUrl('/catalogue');
            });
          }
        } else {
          this.isError = true;
          this.errorMessage = response.message;
          alert(this.errorMessage);
        }
      },
      error: (error) => {
        this.loading = false;
        this.isError = true;
        this.errorMessage = error.message || 'Something went wrong';
      },
    });
  }

  ngOnInit(): void {
    if (this.authentificationService.isAuthenticated()) {
      this.commandeService
        .addCommande(this.panierService.panierFinal())
        .subscribe(() => {
          alert('Commande ajoutée avec succès');
          this.panierService.clearPanier();
        });
      this.router.navigateByUrl('/catalogue/commande');
    }
  }
}

//  le cas next c'est le cas de succès, quand tout marche
// le cas complete c'est le cas de fin de traitement, quand tout est terminé
//  et le cas error c'est le cas d'erreur
