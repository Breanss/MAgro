import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {FieldService} from "../../../_services/field.service";


@Component({
  selector: 'app-dialogedit',
  templateUrl: './dialogedit.component.html',
  styleUrls: ['./dialogedit.component.css']
})
export class DialogeditComponent implements OnInit {

  message = "";

  constructor(private fieldService: FieldService,
              private formBuilder: FormBuilder,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private dialogRef: MatDialog) {
  }

  editForm!: FormGroup;

  ngOnInit(): void {
    this.fieldService.function()
    this.message = this.data.name
    this.editForm = this.formBuilder.group({
      area: '',
      argonomicCategory: '',
      property: ''
    })

    if (this.data) {
      this.editForm.controls['area'].setValue(this.data.area);
      this.editForm.controls['argonomicCategory'].setValue(this.data.argonomicCategory);
      this.editForm.controls['property'].setValue(this.data.property);
    }

  }

  editField() {
    let tmp = true;
    const forms = document.querySelectorAll('.needs-validation');

    Array.prototype.slice.call(forms)
      .forEach(function (form) {
        if (!form.checkValidity()) {
          tmp = false;
        }

      })

    if (tmp) {
      this.data.area = this.editForm.controls['area'].value
      this.data.argonomicCategory = this.editForm.controls['argonomicCategory'].value
      this.data.fieldProperty = this.editForm.controls['property'].value

      this.fieldService.editField(this.data, this.data.fieldId).subscribe(
        (response: any) => {
          this.message = ''
          this.dialogRef.closeAll()

        },
        (error) => {
          console.log(error);
        }
      );
    }
  }
}
