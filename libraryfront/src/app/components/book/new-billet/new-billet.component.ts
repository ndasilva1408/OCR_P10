import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, NgForm} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from '../../../../models/user';
import {BookService} from '../../../../services/book.service';
import {Book} from '../../../../models/book';
import {UserService} from '../../../../services/user.service';
import {TokenStorageService} from '../../../../services/security/token-storage.service';
import {BilletService} from '../../../../services/billet.service';
import {Billet} from '../../../../models/billet';

@Component({
    selector: 'app-new-billet',
    templateUrl: './new-billet.component.html',
    styleUrls: ['./new-billet.component.css']
})
export class NewBilletComponent implements OnInit {
    forms: FormGroup;
    user: User;
    book: Book;
    authorities: string;
    billet: Billet;
    biblioArray = [{
        id: 1,
        name: 'Médiathèque centre-ville de Bayonne'
    }, {
        id: 2,
        name: 'Médiathèque Sainte-Croix de Bayonne'
    }, {
        id: 3,
        name: 'Bibliothèque Universitaire Florence Delay'
    }, {
        id: 4,
        name: 'Bibliothèque Diocésaine de Bayonne'
    }];
    @ViewChild('f', {static: false}) MyForm: NgForm;
    selectedBiblio = [];



    private messageError: string;

    constructor(private token: TokenStorageService, private formBuilder: FormBuilder,
                private bookService: BookService, private billetService: BilletService,
                private userService: UserService,
                private router: Router, private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        this.initform();
        this.initBook();
        this.authorities = this.token.getAuthorities();
        this.initUser();
    }

    private initform() {
        this.forms = this.formBuilder.group(
            {
                bookerId: new FormControl(),
                bookId: new FormControl(),
            });
    }

    initBook() {
        this.activatedRoute.queryParams.subscribe(
            (params) => {
                const id = params.id;
                if (id) {
                    this.bookService.getBook(id).subscribe(data => {
                        this.book = data;
                    });
                }
            });
    }

    initUser() {
        this.userService.getProfil(this.token.getLogin()).subscribe(data => {
            this.user = data;
        });
    }

    save() {
        this.updateBookQty();
        this.saveBillet();
        this.MyForm.form.markAllAsTouched();
    }

    saveBillet() {
        console.log(this.forms.value);
        this.billetService.saveBorrow(this.forms)
            .subscribe(
                response => {
                    console.log('response: ', response);
                },
                error => {
                    console.log('Error: ', error.error.message);
                    this.messageError = error.error.message;
                });
        this.router.navigate(['home']);
    }

    updateBookQty() {
        this.bookService.updateBookQty(this.forms)
            .subscribe(response => {
                    console.log('response: ', response);
                },
                error => {
                    console.log('Error: ', error.error.message);
                    this.messageError = error.error.message;
                });
    }
}
