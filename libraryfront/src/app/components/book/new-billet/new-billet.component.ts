import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from '../../../../models/user';
import {BookService} from '../../../../services/book.service';
import {Book} from '../../../../models/book';
import {Bibliotheque} from '../../../../models/bibliotheque';
import {UserService} from '../../../../services/user.service';
import {TokenStorageService} from '../../../../services/security/token-storage.service';

@Component({
  selector: 'app-new-billet',
  templateUrl: './new-billet.component.html',
  styleUrls: ['./new-billet.component.css']
})
export class NewBilletComponent implements OnInit {
  forms: FormGroup;
  user: User;
  book: Book;
  authorities: string;

  constructor(private token: TokenStorageService, private formBuilder: FormBuilder,
              private bookService: BookService, private userService: UserService,
              private router: Router,  private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.initform();
    this.initBook();
    this.authorities = this.token.getAuthorities();
    this.initUser();
  }

  private initform() {
    this.forms = this.formBuilder.group(
        {
          date: new FormControl(),
        });
  }

  initBook() {
    this.activatedRoute.queryParams.subscribe(
        (params) => {
          const id = params['id'];
          if (id) {
            this.bookService.getBook(id).subscribe(data => {
              this.book = data;
            });
          }
        });
  }

  initUser() {
    this.userService.getProfil(this.token.getLogin()).subscribe(data => {
      this.user = data;
    });
  }

  saveBillet() {
    console.log(this.forms.value);
    console.log('TODO:billetService saveBillet');
  }
}
