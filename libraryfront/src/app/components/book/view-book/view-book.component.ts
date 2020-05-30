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
import {delay} from 'rxjs/operators';


@Component({
    selector: 'app-view-book',
    templateUrl: './view-book.component.html',
    styleUrls: ['./view-book.component.css']
})
export class ViewBookComponent implements OnInit {
    i: number;
    book: Book;
    user: User;
    users: Array<User>;
    books: Array<Book>;
    librarys: Array<Bibliotheque> = [];
    billets: Array<Billet>;
    waitinList: Array<Billet> = [];
    waitListSize = 0;
    authorities: string;
    billet: Billet;
    cantBook = false;
    userId: number;

    constructor(private token: TokenStorageService, private bookService: BookService, private route: Router,
                private activatedRoute: ActivatedRoute, private libraryService: LibraryService,
                private billetService: BilletService, private userService: UserService) {
    }

    ngOnInit() {
        this.initUser();
        this.initLibrarys();
        this.initBook();
        this.initUsers();
        this.authorities = this.token.getAuthorities();
    }

    initBook() {
        this.activatedRoute.queryParams.subscribe(
            (params) => {
                const id = params.id;
                if (id) {
                    this.bookService.getBook(id).subscribe(data => {
                        this.book = data;
                        this.initListBook();
                        this.initWaitinList(id);
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

    private async initWaitinList(id: number) {
        this.waitListSize = await this.bookService.getWaitList(id).toPromise();
        this.userId = await this.userService.getProfilId(this.token.getLogin()).toPromise();
        this.billetService.getWaitingList(id, this.waitListSize)
            .subscribe(list => {
                this.waitinList = list;
            });
        this.billetService.getBorrows().subscribe(borrows => {
            this.billets = borrows;
            this.cantBook = this.billetService.updatecanBorrow(this.billets, this.book.id, this.userId);
        });
        console.log('cantbook', this.cantBook);
    }

    private initLibrarys() {
        this.libraryService.getLibrarys().subscribe(
            data => {
                this.librarys = data;
                console.log('dataLIB : ', data);
            },
            err => {
                console.log('error: ', err.error.message);
            });
    }

    newBillet(id
                  :
                  number
    ) {
        this.route.navigate(['new-billet'], {queryParams: {id}});
    }

    newBilletOnWaitList(id
                            :
                            number
    ) {
        this.route.navigate(['new-billet-waitlist'], {queryParams: {id}});
    }

    initUser() {
        this.userService.getProfil(this.token.getLogin()).subscribe(data => {
            this.user = data;
        });
    }

    viewBook(id
                 :
                 number
    ) {
        this.route.navigate(['book'], {queryParams: {id}});
    }

    initUsers() {
        this.userService.getUsers().subscribe(
            data => {
                this.users = data;
            }
        );
    }
}
