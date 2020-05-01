import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../../../services/security/token-storage.service';
import {BookService} from '../../../services/book.service';
import {Book} from '../../../models/book';
import {Router} from '@angular/router';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    info: any;
    books: Array<Book>;


    constructor(private token: TokenStorageService, private bookService: BookService, private router: Router) {
    }

    ngOnInit() {
        this.initBook();
        this.info = {
            login: this.token.getLogin(),
            token: this.token.getToken(),
            authorities: this.token.getAuthorities()
        };

    }

    private initBook() {
        this.bookService.getBooks().subscribe(
            data => {
                this.books = data;
            }
        );
    }

    viewBook(id: number) {
        this.router.navigate(['book'], {queryParams: {id}});
    }
}
