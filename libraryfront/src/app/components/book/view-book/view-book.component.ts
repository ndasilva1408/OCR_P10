import {Component, Input, OnInit} from '@angular/core';
import {Book} from '../../../../models/book';
import {BookService} from '../../../../services/book.service';
import {ActivatedRoute, Router} from '@angular/router';
import {LibraryService} from '../../../../services/library.service';
import {Bibliotheque} from '../../../../models/bibliotheque';
import {NewBilletComponent} from '../new-billet/new-billet.component';
import {Billet} from '../../../../models/billet';
import {TokenStorageService} from '../../../../services/security/token-storage.service';

@Component({
    selector: 'app-view-book',
    templateUrl: './view-book.component.html',
    styleUrls: ['./view-book.component.css']
})
export class ViewBookComponent implements OnInit {

    book: Book;
    books: Array<Book>;
    librarys: Array<Bibliotheque>;
    billetList: Array<Billet>;
    authorities: string;

    constructor(private token: TokenStorageService, private bookService: BookService, private route: Router,
                private activatedRoute: ActivatedRoute, private libraryService: LibraryService) {
    }

    ngOnInit() {
        this.initLibrarys();
        this.initBook();
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
  /*  private initListBillet() {
        this.billetService.getBillets().subscribe(
            data => {
                this.billetList = data.filter(b => b.titre === this.book.titre && b.auteur === this.book.auteur);
                this.books.forEach(book => {
                    this.librarys.filter(library => book.provenance === ('' + library.name))
                        .forEach(library => book.auteur = library.name);
                });
                console.log('data initListBook: ', this.books);
            });
    }*/

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

}
