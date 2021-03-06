import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Billet} from '../models/billet';
import {FormGroup} from '@angular/forms';
import {Observable, Subject} from 'rxjs';


@Injectable({
    providedIn: 'root'
})

export class BilletService {

    private borrowURL = 'http://localhost:9004/billet-microservice/api/billet-microservice';
    private cantBook: boolean;

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

    saveBorrowForWaitList(form: FormGroup): Observable<FormGroup> {
        return this.http.post<FormGroup>(this.borrowURL + '/addBilletForWaitList', form.value);
    }


    updateBorrowStatus(id: any) {
        console.log('id to update', id);
        return this.http.put<Billet>(this.borrowURL + '/extendBillet', {}, {params: {id: id}});
    }


    getWaitingList(id: any, waitingListSize: number) {
        let params = new HttpParams();

        params = params.append('id', id);
        params = params.append('waitingListSize', JSON.stringify(waitingListSize));

        return this.http.get<Array<Billet>>(this.borrowURL + '/getWaitingList', {
            params
        });
    }

    deleteBorrow(id: any): Observable<{}> {
        return this.http.delete<Billet>(this.borrowURL + '/deleteBillet', {
            params: new HttpParams()
                .set('id', id)
        });
    }

    updatecanBorrow(waitinList: Array<Billet>, bookId: number, userId: number) {
        for (let entry of waitinList) {
            if (entry.bookerId === userId.toString()) {
                if (Number(entry.bookId) === bookId) {
                    return this.cantBook = true;
                }
            }
        }
    }
}
