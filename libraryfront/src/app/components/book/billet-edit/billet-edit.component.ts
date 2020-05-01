import {Component, OnInit} from '@angular/core';
import {BilletService} from '../../../../services/billet.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Billet} from '../../../../models/billet';
import {BookService} from '../../../../services/book.service';
import {Book} from '../../../../models/book';
import {User} from '../../../../models/user';
import {UserService} from '../../../../services/user.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
    selector: 'app-borrow-edit',
    templateUrl: './billet-edit.component.html',
    styleUrls: ['./billet-edit.component.css']
})
export class BilletEditComponent implements OnInit {

    billet: Billet;
    forms: FormGroup;
    borrows: Array<Billet>;
    books: Array<Book>;
    book: Book;
    users: Array<User>;


    constructor(private billetService: BilletService, private router: Router,
                private formBuilder: FormBuilder, private activatedRoute: ActivatedRoute,
                private bookService: BookService, private userService: UserService) {
    }

    ngOnInit() {
        this.initBillet();
        this.initBooks();
        this.initUsers();
        this.initForm();
        this.activatedRoute.queryParams.subscribe(
            (params) => {
                const id = params['id'];
                if (id) {
                    this.patchValue(id);
                }
            });
    }

    private initBillet() {
        this.billetService.getBorrows().subscribe(
            data => {
                this.borrows = data;
            }
        );
    }

    private initForm() {
        this.forms = this.formBuilder.group({
            id: [''],
            userID: ['', Validators.required],
            bookID: ['', Validators.required],
            dateStart: [new Date(), Validators.required],
            dateEnd: [new Date(), Validators.required],
            dateExtend: [new Date(), Validators.required],
            isExtend: ['', Validators.required],
        });
    }

    private patchValue(id) {
        this.billetService.getBorrow(id).subscribe(
            data => {
                this.billet = data;
                console.log('borrow to search:', data);
                this.forms.patchValue({
                    id: id,
                    bookerId: data.bookerId,
                    bookId: data.bookId,
                    bookingDate: data.bookingDate,
                    endDate: data.endDate,
                    extendDate: data.extendDate,
                    isExtend: data.isExtend,
                });
                console.log('forms :', this.forms.value);
            });
    }

    onSubmit() {
        if (!this.billet || this.billet.id == null) {
            this.billetService.saveBorrow(this.forms).subscribe(
                () => {
                    this.updateAvaibleStatus();
                    this.router.navigate(['admin']);
                });
        }
    }

    private initBooks() {
        this.bookService.getBooks().subscribe(
            data => {
                this.books = data.filter(value => value.disponible === true);
            }
        );
    }

    private initUsers() {
        this.userService.getUsers().subscribe(
            data => {
                this.users = data;
            }
        );
    }

    updateDateStart() {
        let date = new Date(this.forms.value.dateStart);
        date.setHours(12);
        this.billet.bookingDate = date;
    }

    updateDateEnd() {
        this.billet.endDate = new Date(this.forms.value.dateEnd);
    }

    updateDateExtend() {
        this.billet.extendDate = new Date(this.forms.value.dateExtend);
    }

    //todo : a corriger
    updateAvaibleStatus() {
        this.bookService.getBook(this.forms.value.bookID).subscribe(
            data => {
                this.book = data;
                this.bookService.updateBookStatus(this.book).subscribe(res => {
                    this.initBooks();
                });
            });
    }
}

