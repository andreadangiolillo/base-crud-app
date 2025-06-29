import { Routes } from '@angular/router';

export const USER_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./pages/user-crud/user-crud.component').then(m => m.UserCrudComponent),
  }
];
