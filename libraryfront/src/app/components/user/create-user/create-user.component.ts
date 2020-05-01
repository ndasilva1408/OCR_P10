import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {User} from '../../../../models/user';
import {UserService} from '../../../../services/user.service';
import {Router} from '@angular/router';
import {AuthService} from '../../../../services/security/auth.service';

@Component({
    selector: 'app-create-user',
    templateUrl: './create-user.component.html',
    styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
    forms: FormGroup;
    user: User;

    private messageError: string;

    constructor(private formBuilder: FormBuilder, private userService: UserService,
                private authService: AuthService, private router: Router) {
    }

    ngOnInit() {
        this.initform();
    }

    private initform() {
        this.forms = this.formBuilder.group(
            {
                login: new FormControl(),
                prenom: new FormControl(),
                nom: new FormControl(),
                mail: new FormControl('', Validators.compose([
                    Validators.required,
                    Validators.pattern('[A-Za-z0-9._%-]+@[A-Za-z0-9._%-]+\\.[a-z]{2,3}')
                ])),
                password: new FormControl(),
                adresse: new FormControl(),
            });
    }

    private saveUser() {
        console.log(this.forms.value);

        this.authService.saveUser(this.forms)
            .subscribe(
                response => {
                    console.log('response: ', response);
                },
                err => {
                    console.log('Error: ', err.error.message);
                    this.messageError = err.error.message;
                });
        this.router.navigate(['home']);
    }

}
