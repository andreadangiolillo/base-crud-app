import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { UsersStore } from '../../store/users.store';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { User } from '../../models/user.model';
import { UserFormComponent } from '../../components/user-form/user-form.component';
import { UserFilterComponent } from '../../components/user-filter/user-filter.component';

@Component({
  standalone: true,
  selector: 'app-user-crud',
  templateUrl: './user-crud.component.html',
  styleUrl: './user-crud.component.scss',
  providers: [UsersStore],
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    UserFormComponent
  ]
})
export class UserCrudComponent implements OnInit {

  readonly _store = inject(UsersStore);
  readonly fb = inject(FormBuilder);
  readonly dialog = inject(MatDialog);

  ngOnInit(): void {
    this._store.findAll();
  }

  displayedColumns = ['id', 'name', 'surname', 'email', 'actions'];
  userForm!: FormGroup;
  editingUser: User | null = null;
  @ViewChild('userDialog') userDialog!: TemplateRef<any>;
  @ViewChild(UserFormComponent, { static: false }) userFormComponent!: UserFormComponent;

  onNewUser() {
    const dialogRef = this.dialog.open(UserFormComponent, {
      data: null
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this._store.create(result);
      }
    });
  }

  onEditUser(user: User) {
    const dialogRef = this.dialog.open(UserFormComponent, {
      data: user
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this._store.update(user.id, result);
      }
    });

  }

  onDeleteUser(id: number) {
    this._store.delete(id);
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (!input.files || input.files.length === 0) return;

    const file = input.files[0];
    const formData = new FormData();
    formData.append('file', file);

    this._store.uploadCsv(formData);

    input.value = '';
  }

  onFilterClick() {
    const dialogRef = this.dialog.open(UserFilterComponent, {
      data: this._store.userFilter()
    });

    dialogRef.afterClosed().subscribe(filter => {
      this._store.updateFilter(filter);
    });
  }

}
