import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

import {JwtResponse} from './JwtReponse';
import {AuthLoginInfo} from './login-info';
import {FormGroup} from '@angular/forms';
import {User} from '../../models/user';


const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private URL = 'http://localhost:9004/api/auth';


    constructor(private http: HttpClient) {
    }

    attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
        return this.http.post<JwtResponse>(this.URL + '/signin', credentials, httpOptions);
    }

    saveUser(form: FormGroup): Observable<FormGroup> {
        return this.http.post<FormGroup>(this.URL + '/signup', form.value);
    }

    deleteUser(id: any) {
        return this.http.delete<User>(this.URL + '/deleteUser', {
            params: new HttpParams()
                .set('id', id)
        });
    }
}
