import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./features/home/pages/home/home.component').then(m => m.HomeComponent)
  },
  {
    path: 'users',
    loadChildren: () =>
      import('./features/users/users.routes').then(m => m.USER_ROUTES),
  }
];
