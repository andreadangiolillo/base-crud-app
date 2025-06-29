import { patchState, signalStore, withMethods, withState } from '@ngrx/signals';
import { User } from '../models/user.model';
import { inject } from '@angular/core';
import { UserApiService } from '../data-access/user-api.service';
import { firstValueFrom } from 'rxjs';
import { UserFilter } from '../models/user-filter.model';

type UsersState = {
  users: User[];
  userFilter: UserFilter | null;
};

const initialState: UsersState = {
  users: [],
  userFilter: null
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
    },

    async create(user: User): Promise<void> {
      await firstValueFrom(usersService.create(user));
      const users = await firstValueFrom(usersService.refresh(store.userFilter()));
      patchState(store, { users });
    },

    async update(id: number, user: User): Promise<void> {
      await firstValueFrom(usersService.update(id, user));
      const users = await firstValueFrom(usersService.refresh(store.userFilter()));
      patchState(store, { users });
    },

    async delete(id: number): Promise<void> {
      await firstValueFrom(usersService.delete(id));
      const users = await firstValueFrom(usersService.refresh(store.userFilter()));
      patchState(store, { users });
    },

    async uploadCsv(fileData: FormData): Promise<void> {
      await firstValueFrom(usersService.uploadCsv(fileData));
      const users = await firstValueFrom(usersService.refresh(store.userFilter()));
      patchState(store, { users });
    },

    async updateFilter(userFilter: UserFilter | null) {
      patchState(store, { userFilter });
      if (userFilter) {
        const users = await firstValueFrom(usersService.search(userFilter));
        patchState(store, { users });
      } else {
        const users = await firstValueFrom(usersService.findAll());
        patchState(store, { users });
      }
    },

  }))
  
);
