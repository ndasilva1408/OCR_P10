import {Component, OnInit} from '@angular/core';
import {LibraryService} from '../../../../services/library.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Bibliotheque} from '../../../../models/bibliotheque';

@Component({
    selector: 'app-library-edit',
    templateUrl: './library-edit.component.html',
    styleUrls: ['./library-edit.component.css']
})
export class LibraryEditComponent implements OnInit {

    forms: FormGroup;
    library: Bibliotheque;


    constructor(private libraryService: LibraryService, private activatedRoute: ActivatedRoute, private formBuilder: FormBuilder, private router: Router) {
    }

    ngOnInit() {
        this.initForm();
        this.activatedRoute.queryParams.subscribe(
            (params) => {
                const id = params['id'];
                if (id) {
                    this.patchValue(id);
                    this.library.id = id;
                }
            });
    }

    private initForm() {
        this.forms = this.formBuilder.group({
            id: [''],
            name: ['', Validators.required],
            phone: ['', Validators.required],
            adresse: ['', Validators.required],
        });
    }

    private patchValue(id: any) {
        this.libraryService.getLibrary(id).subscribe(
            data => {
                this.library = data;
                console.log('library to search:', data);
                this.forms.patchValue({
                    name: data.name,
                    phone: data.phone,
                    adresse: data.adresse,
                    id: id
                });
                console.log('forms :', this.forms.value);
            });
    }

    onSubmit() {
        if (!this.library || this.library.id == null) {
            this.libraryService.saveLibrary(this.forms).subscribe(
                next => this.router.navigate(['admin'])
            );
        } else {
            this.libraryService.updateLibrary(this.forms).subscribe(
                next => this.router.navigate(['admin'])
            );
        }
    }
}
