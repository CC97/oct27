import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

interface UserUpdate {
  id: number;
  uid: number;
  email: string;
  password: string;
  name: string;
  phone: string;
}

interface UserSearch {
  id: number;
  email: string;
  password: string;
  name: string;
  phone: string;
  admin: boolean;
}

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
  createUser = {
    email: '',
    name: '',
    phone: '',
  };

  updates: UserUpdate[] = [];
  searchResults: UserSearch[] = [];

  constructor(private httpClient: HttpClient) {}

  ngOnInit(): void {
    this.fetchUpdates();
  }

  fetchUpdates(): void {
    this.httpClient
      .get<UserUpdate[]>('http://localhost:8080/updates')
      .subscribe({
        next: (data) => (this.updates = data),
        error: (error) => console.error('Error fetching updates:', error),
      });
  }

  onSubmit() {
    this.httpClient
      .post('http://localhost:8080/users', this.createUser)
      .subscribe({
        next: (response: any) => {
          alert('User created.');
        },
        error: (e) => {
          alert('failed.');
        },
      });
  }

  onConfirm(update: UserUpdate): void {
    const updateData = {
      id: update.uid,
      email: update.email,
      password: update.password,
      name: update.name,
      phone: update.phone,
    };
    this.httpClient
      .put(`http://localhost:8080/users/${updateData.id}`, updateData)
      .subscribe({
        next: (response) => console.log('User updated successfully', response),
        error: (error) => console.error('Error updating user:', error),
      });
    this.onDelete(update.id);
  }

  onDelete(updateId: number): void {
    this.httpClient
      .delete(`http://localhost:8080/updates/${updateId}`)
      .subscribe({
        next: (response) => {
          console.log('User deleted successfully', response);
          this.fetchUpdates(); // Refresh the list after deletion
        },
        error: (error) => console.error('Error deleting update:', error),
      });
  }

  onSearch(value: string): void {
    if (!value) {
      this.searchResults = [];
      return;
    }

    this.httpClient
      .post<UserSearch[]>('http://localhost:8080/users/search', {
        value: value,
      })
      .subscribe({
        next: (data) => (this.searchResults = data),
        error: (error) => console.error('Error searching users:', error),
      });
  }
}
