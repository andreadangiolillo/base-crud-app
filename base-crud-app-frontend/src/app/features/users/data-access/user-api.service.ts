import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';
import { UserFilter } from '../models/user-filter.model';

@Injectable({ providedIn: 'root' })
export class UserApiService {
  private readonly baseUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  findAll(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/findAll`);
  }

  findById(id: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${id}`);
  }

  create(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/create`, user);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  update(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.baseUrl}/${id}`, user);
  }

  uploadCsv(formData: FormData) {
    return this.http.post(`${this.baseUrl}/upload`, formData);
  }

  search(params: { name?: string; surname?: string }) {
    return this.http.get<User[]>(`${this.baseUrl}/search`, { params });
  }

  refresh(userFilter: UserFilter | null): Observable<User[]> {
    if (userFilter) {
      const params: any = {};
      if (userFilter.name) params.name = userFilter.name;
      if (userFilter.surname) params.surname = userFilter.surname;
      return this.search(params);
    } else {
      return this.findAll();
    }
  }


}
