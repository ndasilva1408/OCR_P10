import {Component, Input, OnInit} from '@angular/core';
import {Book} from '../../../../models/book';
import {BookService} from '../../../../services/book.service';
import {ActivatedRoute, Router} from '@angular/router';
import {LibraryService} from '../../../../services/library.service';
import {Bibliotheque} from '../../../../models/bibliotheque';
import {Billet} from '../../../../models/billet';
import {TokenStorageService} from '../../../../services/security/token-storage.service';
import {BilletService} from '../../../../services/billet.service';
import {UserService} from '../../../../services/user.service';
import {User} from '../../../../models/user';

@Component({
    selector: 'app-view-book',
    templateUrl: './view-book.component.html',
    styleUrls: ['./view-book.component.css']
})
export class ViewBookComponent implements OnInit {

    book: Book;
    user: User;
    users: Array<User>;
    books: Array<Book>;
    librarys: Array<Bibliotheque>;
    billets: Array<Billet>;
    waitinList: Array<Billet>;
    authorities: string;
    billet: Billet;

    constructor(private token: TokenStorageService, private bookService: BookService, private route: Router,
                private activatedRoute: ActivatedRoute, private libraryService: LibraryService,
                private billetService: BilletService, private userService: UserService) {
    }

    ngOnInit() {
        this.initUser();
        this.initLibrarys();
        this.initBook();
        this.initBillet();
        this.initUsers();
        this.initWaitinList();
        this.authorities = this.token.getAuthorities();
    }

    initBook() {
        this.activatedRoute.queryParams.subscribe(
            (params) => {
                const id = params['id'];
                if (id) {
                    this.bookService.getBook(id).subscribe(data => {
                        this.book = data;
                        this.initListBook();
                    });
                }
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
    private initWaitinList() {
        this.billetService.getBorrows().subscribe(
            data => {
                this.waitinList = data.filter(t => t.isOnWaitList === true);
                console.log('dataBillets :', data);
            },
            err => {
                console.log('error: ', err.error.message);
            });
    }

    private initBillet() {
        this.billetService.getBorrows().subscribe(
            data => {
                this.billets = data;
                console.log('dataBillets :', data);
            },
            err => {
                console.log('error: ', err.error.message);
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

    newBillet(id: number) {
        this.route.navigate(['new-billet'], {queryParams: {id}});
    }
    newBilletOnWaitList(id: number) {
        this.route.navigate(['new-billet-waitlist'], {queryParams: {id}});
    }

    initUser() {
        this.userService.getProfil(this.token.getLogin()).subscribe(data => {
            this.user = data;
        });
    }

    initUsers() {
        this.userService.getUsers().subscribe(
            data => {
                this.users = data;
            }
        );
    }



}
