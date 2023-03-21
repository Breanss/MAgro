import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {FieldService} from "../../../_services/field.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";

import {Router} from "@angular/router";

@Component({
  selector: 'app-dialogdelete',
  templateUrl: './dialogdelete.component.html',
  styleUrls: ['./dialogdelete.component.css']
})
export class DialogdeleteComponent implements OnInit {


  constructor(private fieldService: FieldService,
              private formBuilder: FormBuilder, @Inject(MAT_DIALOG_DATA)
              public data: any, private dialogRef: MatDialog,
              private router: Router,) {
  }

  public message = "";
  public id = 0;

  ngOnInit(): void {
    this.message = this.data.name;
  }

  public deleteField() {
    this.fieldService.deleteField(this.data.fieldId).subscribe(
      (response) => {
        this.dialogRef.closeAll();
        this.router.navigate(['/field']).then(() => {
            window.location.reload();
          }
        );
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
