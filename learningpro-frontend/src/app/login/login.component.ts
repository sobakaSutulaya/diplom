import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpParams } from '@angular/common/http';
import { AuthService } from '../common/auth.service';
import { LocalStorageService } from '../common/local-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  invalidLogin = false;

  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService,
    private localStorageService: LocalStorageService) { }

  onSubmit() {
    if (this.loginForm.invalid) {
      this.invalidLogin = true;
      return;
    }
    const body = new HttpParams()
      .set('username', this.loginForm.controls.username.value)
      .set('password', this.loginForm.controls.password.value)
      .set('grant_type', 'password');

    this.authService.login(body.toString()).subscribe(data => {
      this.localStorageService.setItem('token', data);
      console.log(this.localStorageService.getItem('token'));
      this.saveUserInStorage(this.loginForm.controls.username.value);
      this.authService.setLoggedIn(true);
      this.router.navigate(['/home']);
    }, error => {
      console.log(error);
    });
  }

  private saveUserInStorage(login: string): void {
    this.saveUserIdInStorage(login);
    this.saveRoleInStorage();
  }

  private saveUserIdInStorage(login: string): void {
    this.authService.getUserId(login).subscribe(userId => {
      console.log(userId);
      this.localStorageService.setItem('userId', userId);
    }, error => {
      this.localStorageService.clear();
      console.log(error);
    });
  }

  private saveRoleInStorage(): void {
    this.authService.getPrincipal().subscribe(principal => {
      console.log(principal.authorities[0].authority);
      const userRole = principal.authorities[0].authority;
      this.localStorageService.setItem('userRole', userRole);
    }, error => {
      this.localStorageService.clear();
      console.log(error);
    });
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required])],
      password: ['', Validators.required]
    });
  }

}
