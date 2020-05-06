import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {FormGroup} from '@angular/forms';
import {Observable} from 'rxjs';
import {User} from '../models/user';
import {TokenStorageService} from './security/token-storage.service';


@Injectable({
    providedIn: 'root'
})
export class UserService {

    private userURL = 'http://localhost:9004/client-microservice/api/client-microservice';

    constructor(private http: HttpClient, private token: TokenStorageService) {
    }


    getProfil(login: string) {
        return this.http.get<User>(this.userURL + '/myProfil',
            {
                params: new HttpParams()
                    .set('login', this.token.getLogin())
            });
    }

    getUsers() {
        return this.http.get<Array<User>>(this.userURL + '/getAll');
    }
    getUser(id: any): Observable<User> {
        return this.http.get<User>(this.userURL + '/getClient', {
            params: new HttpParams()
                .set('id', id),
        });
    }

    getUserLogin(id: any): Observable<User> {
        return this.http.get<User>(this.userURL + '/getClientLogin', {
            params: new HttpParams()
                .set('id', id),
        });
    }

    deleteUser(idUser: any): Observable<{}> {
        return this.http.delete<User>(this.userURL + '/deleteClient', {
            params: new HttpParams()
                .set('id', idUser)

        });
    }
}
