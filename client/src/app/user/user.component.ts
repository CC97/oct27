import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  userInfo: any;
  email: string = '';
  password: string = '';
  phone: string = '';
  name: string = '';

  constructor(private route: ActivatedRoute, private httpClient: HttpClient) {}

  ngOnInit() {
    this.route.queryParams.subscribe((params) => {
      if (params['data']) {
        try {
          this.userInfo = JSON.parse(decodeURIComponent(params['data']));
        } catch (error) {}
      }
    });
  }

  onSubmit() {
    const updateInfo = {
      uid: this.userInfo.id,
      email: this.email,
      password: this.password,
      phone: this.phone,
      name: this.name,
    };

    this.httpClient.post('http://localhost:8080/updates', updateInfo).subscribe(
      (response: any) => {
        alert('update request send.');
      },
      (e) => {
        alert('fail');
      }
    );
  }
}
