import {Component, OnInit} from '@angular/core';
import {Bibliotheque} from '../../../../models/bibliotheque';
import {LibraryService} from '../../../../services/library.service';
import {Router} from '@angular/router';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';

@Component({
    selector: 'app-view-librarys',
    templateUrl: './view-librarys.component.html',
    styleUrls: ['./view-librarys.component.css']
})
export class ViewLibrarysComponent implements OnInit {

    librarys: any;


    constructor(private libraryService: LibraryService, private router: Router, private formBuilder: FormBuilder) {
    }

    ngOnInit() {
        this.initformLibrary();
    }

    private initformLibrary() {

        this.libraryService.getLibrarys().subscribe(
            data => {
                this.librarys = data;
                console.log('data : ', data);
            }),
            err => {
                console.log('error: ', err.error.message);
            };
    }
}
