import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from '../../../services/security/token-storage.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  authorities: string;
  constructor(private token: TokenStorageService, private router: Router) { }

  ngOnInit() {
    this.authorities = this.token.getAuthorities();
  }

  logout() {
    this.token.signOut();
    window.location.reload();
  }
}
