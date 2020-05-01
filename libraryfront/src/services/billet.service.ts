import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Billet} from '../models/billet';
import {FormGroup} from '@angular/forms';
import {Observable} from 'rxjs';


@Injectable({
    providedIn: 'root'
})

export class BilletService {

    private borrowURL = 'http://localhost:9004/billet-microservice/api/billet-microservice';

    constructor(private http: HttpClient) {
    }

    getBorrows() {
        return this.http.get<Array<Billet>>(this.borrowURL + '/getAll');
    }

    getBorrowsByUserID(bookerId: any) {
        return this.http.get<Array<Billet>>(this.borrowURL + '/getBookerBillets', {
            params: new HttpParams()
                .set('bookerId', bookerId)
        });
    }

    getBorrow(id: string) {
        return this.http.get<Billet>(this.borrowURL + '/getBorrow', {
            params: new HttpParams()
                .set('id', id),
        });
    }

    saveBorrow(form: FormGroup): Observable<FormGroup> {
        return this.http.post<FormGroup>(this.borrowURL + '/addBillet', form.value);
    }


    updateBorrowStatus(id: any) {
        console.log('id to update', id);
        return this.http.put<Billet>(this.borrowURL + '/extendBillet', {}, {params: {id: id}});
    }

    deleteBorrow(id: any): Observable<{}> {
        return this.http.delete<Billet>(this.borrowURL + '/deleteBillet', {
            params: new HttpParams()
                .set('id', id)
        });
    }

}
