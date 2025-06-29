import { patchState, signalStore, withMethods, withState } from '@ngrx/signals';
import { User } from '../models/user.model';
import { inject } from '@angular/core';
import { UserApiService } from '../data-access/user-api.service';
import { firstValueFrom } from 'rxjs';

type UsersState = {
  users: User[];
};

const initialState: UsersState = {
  users: []
};

export const UsersStore = signalStore(
  withState(initialState),
  withMethods((store, usersService = inject(UserApiService)) => ({
    async findAll(): Promise<void> {
      const users = await firstValueFrom(usersService.findAll());
      patchState(store, { users });
    },

    async findById(id: number): Promise<User | undefined> {
      return await firstValueFrom(usersService.findById(id));
      const users = await firstValueFrom(usersService.findAll());
      patchState(store, { users });
    },

    async create(user: User): Promise<void> {
      await firstValueFrom(usersService.create(user));
      const users = await firstValueFrom(usersService.findAll());
      patchState(store, { users });
    },

    async update(id: number, user: User): Promise<void> {
      await firstValueFrom(usersService.update(id, user));
      const users = await firstValueFrom(usersService.findAll());
      patchState(store, { users });
    },

    async delete(id: number): Promise<void> {
      await firstValueFrom(usersService.delete(id));
      const users = await firstValueFrom(usersService.findAll());
      patchState(store, { users });
    },

    async uploadCsv(fileData: FormData): Promise<void> {
      await firstValueFrom(usersService.uploadCsv(fileData));
      const users = await firstValueFrom(usersService.findAll());
      patchState(store, { users });
    },

    async search(name: string, surname: string) {
      const users = await firstValueFrom(usersService.search({name, surname}));
      patchState(store, { users });
    },

  }))
);
