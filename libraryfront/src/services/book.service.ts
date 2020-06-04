import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {FormGroup} from '@angular/forms';
import {Observable} from 'rxjs';
import {Book} from '../models/book';
import {Billet} from "../models/billet";

@Injectable({
    providedIn: 'root'
})

export class BookService {
    private bookURL = 'http://localhost:9004/book-microservice/api/book-microservice';

    constructor(private http: HttpClient) {
    }

    getBook(id: any): Observable<Book> {
        return this.http.get<Book>(this.bookURL + '/getBook', {
            params: new HttpParams()
                .set('id', id),
        });
    }

    searchBook(keyword: string): Observable<Array<Book>> {
        return this.http.get<Array<Book>>(this.bookURL + '/findByKeyword', {
            params: new HttpParams()
                .set('keyword', keyword)
        });
    }

    getBooks(): Observable<Array<Book>> {
        return this.http.get<Array<Book>>(this.bookURL + '/getAll');
    }
    getWaitList(id: any): Observable<number> {
        return this.http.get<number>(this.bookURL + '/getBookWaitingListSize', {
            params: new HttpParams()
                .set('id', id)
        });
    }


    saveBook(form: FormGroup): Observable<FormGroup> {
        console.log('book form: ', form.value);
        return this.http.post<FormGroup>(this.bookURL + '/addBook', form.value);
    }

    updateBook(form: FormGroup): Observable<Book> {
        return this.http.put<Book>(this.bookURL + '/updateBook', form.value);
    }

    updateBookQty(bookId: any) {
        console.log('id to update', bookId);
        return this.http.put<Book>(this.bookURL + '/updateBookQty', {}, {params: {bookId: bookId}});
    }
    upBookQty(bookId: any) {
        console.log('id to up', bookId);
        return this.http.put<Book>(this.bookURL + '/upBookQty', {}, {params: {bookId: bookId}});
    }

    updateBookPositionWaitList(bookId: any) {
        console.log('id to update', bookId);
        return this.http.put<Book>(this.bookURL + '/updatePositionWaitListAdd', {}, {params: {bookId: bookId}});
    }


    updateBookStatus(book: Book): Observable<Book> {
        console.log('book to update status', book);
        return this.http.put<Book>(this.bookURL + '/updateBookStatus', book);
    }

    generateBookWaitingList(id: any) {
        return this.http.get<Array<Book>>(this.bookURL + '/getBookWaitingList', {
            params: new HttpParams()
                .set('id', id),
        });

    }

    deleteBook(idBook: any): Observable<{}> {
        console.log('delete id: ', idBook);
        return this.http.delete<Book>(this.bookURL + '/deleteBook', {
            params: new HttpParams()
                .set('id', idBook)
        });
    }

}
