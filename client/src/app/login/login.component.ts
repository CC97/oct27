import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private httpClient: HttpClient, private router: Router) {}

  login() {
    const user = {
      email: this.email,
      password: this.password,
    };

    this.httpClient.post('http://localhost:8080/users/login', user).subscribe({
      next: (response: any) => {
        const userInfo = encodeURIComponent(JSON.stringify(response));

        if (response.admin) {
          this.router.navigate(['/admin'], { queryParams: { data: userInfo } });
        } else {
          this.router.navigate(['/user'], { queryParams: { data: userInfo } });
        }
      },
      error: (e) => {
        this.errorMessage =
          e.error.message || 'An error occurred during login.';
      },
    });
  }
}
