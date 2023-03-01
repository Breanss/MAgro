import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {FieldService} from "../../../_services/field.service";
import {FieldsComponent} from "../fields/fields.component";


@Component({
  selector: 'app-dialogedit',
  templateUrl: './dialogedit.component.html',
  styleUrls: ['./dialogedit.component.css']
})
export class DialogeditComponent implements OnInit {

  message="";
  constructor(private fieldService:FieldService, private formBuilder: FormBuilder, @Inject(MAT_DIALOG_DATA) public data: any, private dialogRef: MatDialog) {}
  fieldComponent: FieldsComponent | undefined;
  editForm!:FormGroup;
  ngOnInit(): void {
    this.fieldService.function()
    this.message=this.data.fieldName
    this.editForm=this.formBuilder.group({
      fieldArea:'',
      fieldArgonomicCategory:'',
      fieldProperty:''
    })

      if(this.data) {
        this.editForm.controls['fieldArea'].setValue(this.data.fieldArea);
        this.editForm.controls['fieldArgonomicCategory'].setValue(this.data.fieldArgonomicCategory);
        this.editForm.controls['fieldProperty'].setValue(this.data.fieldProperty);
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
    this.data.fieldArea = this.editForm.controls['fieldArea'].value
    this.data.fieldArgonomicCategory = this.editForm.controls['fieldArgonomicCategory'].value
    this.data.fieldProperty = this.editForm.controls['fieldProperty'].value

    if (tmp) {
      this.fieldService.editField(this.data, this.data.fieldId).subscribe(
        (response: any) => {
          this.message = ''
          this.fieldComponent?.allField()
          this.dialogRef.closeAll()
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }
}
