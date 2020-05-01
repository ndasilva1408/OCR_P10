import { Component, OnInit } from '@angular/core';
import {AuthLoginInfo} from '../../../../services/security/login-info';
import {AuthService} from '../../../../services/security/auth.service';
import {TokenStorageService} from '../../../../services/security/token-storage.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles = '[]';
  private loginInfo: AuthLoginInfo;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router) {
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }
  }

  onSubmit() {
    console.log(this.form);

    this.loginInfo = new AuthLoginInfo(
      this.form.login,
      this.form.password);


    this.authService.attemptAuth(this.loginInfo).subscribe(
      response => {
        this.tokenStorage.saveToken(response.token);
        this.tokenStorage.saveLogin(response.login);
        this.tokenStorage.saveAuthorities(response.authorities);
        console.log(response.login);
        console.log(response.token + ' ' + response.authorities);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        this.router.navigate(['/home']);
      },
      error => {
        console.log('error:', error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  navigateToRegister() {
    this.router.navigateByUrl('/create-user');
  }
}
