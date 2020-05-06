import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {User} from '../../../../models/user';
import {Book} from '../../../../models/book';
import {Billet} from '../../../../models/billet';
import {Bibliotheque} from '../../../../models/bibliotheque';
import {TokenStorageService} from '../../../../services/security/token-storage.service';
import {BookService} from '../../../../services/book.service';
import {BilletService} from '../../../../services/billet.service';
import {UserService} from '../../../../services/user.service';
import {LibraryService} from '../../../../services/library.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-new-billet-waitlist',
  templateUrl: './new-billet-waitlist.component.html',
  styleUrls: ['./new-billet-waitlist.component.css']
})
export class NewBilletWaitlistComponent implements OnInit {
  forms: FormGroup;
  user: User;
  book: Book;
  billets: Array<Billet>;
  authorities: string;
  billet: Billet;
  books: Array<Book>;
  librarys: Array<Bibliotheque>;
  selectedBiblio = [];
  bookId: number;


  private messageError: string;

  constructor(private token: TokenStorageService, private formBuilder: FormBuilder,
              private bookService: BookService, private billetService: BilletService,
              private userService: UserService, private libraryService: LibraryService,
              private router: Router, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.initform();
    this.initBook();
    this.authorities = this.token.getAuthorities();
    this.initUser();
    this.initLibrarys();
    this.initListBook();
    this.initBillet();
  }

  private initform() {
    this.forms = this.formBuilder.group(
        {
          bookerId: new FormControl(),
          bookId: new FormControl(),
          biblioId: new FormControl(),
        });
  }

  initBook() {
    this.activatedRoute.queryParams.subscribe(
        (params) => {
          const id = params.id;
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

  save() {
    this.saveBillet();
  }

  saveBillet() {
    console.log(this.forms.value);
    this.billetService.saveBorrowForWaitList(this.forms)
        .subscribe(
            response => {
              console.log('response: ', response);
            },
            error => {
              console.log('Error: ', error.error.message);
              this.messageError = error.error.message;
            });
    this.router.navigate(['home']);
  }
  private initBillet() {
    this.billetService.getBorrowsByUserID(this.user.id).subscribe(
        data => {
          this.billets = data;
          this.billets.forEach(billet => {
            this.books.filter(book => ('' + book.id) === billet.bookId).forEach(book => billet.bookId = book.titre);
          });
        });
  }
  private initListBook() {
    this.bookService.getBooks().subscribe(
        data => {
          this.books = data.filter(b => b.titre === this.book.titre && b.auteur === this.book.auteur);
          this.books.forEach(book => {
            this.librarys.filter(library => book.provenance === ('' + library.name))
                .forEach(library => book.auteur = library.name);
          });
          console.log('data initListBook: ', this.books);
        });
  }
  private initLibrarys() {
    this.libraryService.getLibrarys().subscribe(
        data => {
          this.librarys = data;
          console.log('data : ', data);
        },
        err => {
          console.log('error: ', err.error.message);
        });
  }
}
