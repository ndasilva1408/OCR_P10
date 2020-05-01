import {Component, OnInit} from '@angular/core';
import {Book} from '../../../../models/book';
import {LibraryService} from '../../../../services/library.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BookService} from '../../../../services/book.service';
import {Bibliotheque} from '../../../../models/bibliotheque';

@Component({
    selector: 'app-search-book',
    templateUrl: './search-book.component.html',
    styleUrls: ['./search-book.component.css']
})
export class SearchBookComponent implements OnInit {

    book: Book;
    searchText: string;
    books: Array<Book>;
    librarys: Array<Bibliotheque>;



    constructor(private libraryService: LibraryService, private bookService: BookService, private router: Router) {
    }

    ngOnInit() {
        this.initLibrarys();
    }

    private initLibrarys() {
        this.libraryService.getLibrarys().subscribe(
            data => {
                this.librarys = data;
                this.initBooks();
                console.log('data : ', data);
            },
            err => {
                console.log('error: ', err.error.message);
            });
    }

    private initBooks() {
        this.bookService.getBooks().subscribe(
            data => {
                this.books = data;
                console.log('data : ', data);
                this.books.forEach(book => {
                    this.librarys.filter(library => book.provenance === ('' + library.id))
                        .forEach(library => book.auteur = library.name);
                });
            },
            err => {
                console.log('error: ', err.error.message);
            });

    }


    viewBook(id: number) {
        this.router.navigate(['book'], {queryParams: {id}});
    }

    searchBook(keyword: string) {
        this.bookService.searchBook(keyword).subscribe(
            data => {
                this.books = data;
                console.log('data: ', data);
                this.books.forEach(book => {
                    this.librarys.filter(library => book.provenance === ('' + library.id))
                        .forEach(library => book.auteur = library.name);
                });
            },
            err => {
                console.log('error: ', err.error.message);
            });
    }

}
