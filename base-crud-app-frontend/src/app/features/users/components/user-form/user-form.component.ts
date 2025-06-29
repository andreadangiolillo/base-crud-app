import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { User } from '../../models/user.model';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrl: './user-form.component.scss',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatButtonModule,
    MatIconModule,
  ]
})
export class UserFormComponent {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<UserFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: User | null
  ) {
    this.form = this.fb.group({
      id: [data?.id ?? null],
      name: [data?.name ?? '', Validators.required],
      surname: [data?.surname ?? '', Validators.required],
      email: [data?.email ?? '', [Validators.required, Validators.email]],
    });
  }

  save() {
    if (this.form.valid) {
      this.dialogRef.close(this.form.value);
    }
  }

  cancel() {
    this.dialogRef.close();
  }
}
