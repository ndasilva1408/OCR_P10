import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

import {FormGroup} from '@angular/forms';
import {Observable} from 'rxjs';
import {Bibliotheque} from '../models/bibliotheque';

@Injectable({
    providedIn: 'root'
})
export class LibraryService {

    private libraryURL = 'http://localhost:9004/bibliotheque-microservice/api/bibliotheque-microservice';

    constructor(private http: HttpClient) {
    }

    getLibrary(idLibrary: string) {
        return this.http.get<Bibliotheque>(this.libraryURL + '/getBiblio', {
            params: new HttpParams()
                .set('id', idLibrary),
        });
    }

    getLibrarys(): Observable<Array<Bibliotheque>> {
        return this.http.get<Array<Bibliotheque>>(this.libraryURL + '/getAll');
    }

    saveLibrary(form: FormGroup): Observable<FormGroup> {
        return this.http.post<FormGroup>(this.libraryURL + '/addBiblio', form.value);
    }

    updateLibrary(form: FormGroup): Observable<Bibliotheque> {
        console.log('Bibliotheque:', form.value);
        return this.http.put<Bibliotheque>(this.libraryURL + '/updateLibrary', form.value);
    }

    deleteLibrary(idLibrary: any): Observable<{}> {
        return this.http.delete<Bibliotheque>(this.libraryURL + '/deleteBiblio', {
            params: new HttpParams()
                .set('id', idLibrary)
        });
    }
}
