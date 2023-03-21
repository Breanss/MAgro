import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {FieldService} from "../../../_services/field.service";
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {DialogeditComponent} from "../dialogedit/dialogedit.component";


import {MapComponent} from "../map/map.component";
import {DialogdeleteComponent} from "../dialogdelete/dialogdelete.component";


@Component({
  selector: 'app-fields',
  templateUrl: './fields.component.html',
  styleUrls: ['./fields.component.css']
})

export class FieldsComponent implements OnInit {


  @ViewChild("MapComponent") component?: MapComponent;


  public dataSource: any;

  constructor(private fieldService: FieldService, public dialog: MatDialog) {
  }

  public displayedColumns: string[] = ['name', 'numberRegistration', 'area', 'argonomicCategory', 'property', 'fieldAction'];

  ngOnInit(): void {
    this.allField();
  }

  public openDialogEdit(row: any) {
    this.dialog.open(DialogeditComponent, {
      data: row
    });
  }

  public openDialogDelete(row: any) {
    this.dialog.open(DialogdeleteComponent, {
      data: row
    });
  }


  public pdfFieldUser() {
    this.fieldService.pdfFieldUser().subscribe(x => {
      const blob = new Blob([x], {type: 'application/pdf'});
      const data = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = data;
      link.download = "pola.pdf";
      link.dispatchEvent(new MouseEvent('click', {bubbles: true, cancelable: true, view: window}));
      setTimeout(function () {
        window.URL.revokeObjectURL(data);
        link.remove();
      }, 100)
    })

  }

  public allField() {
    this.fieldService.allFieldUser().subscribe(
      (response) => {
        console.log(response)
        this.dataSource = response
      },
      (error) => {
        console.log(error);
      }
    );
  }


  public plotMap(teryt: string, number: String) {
    this.component?.getParcelById(teryt + '.' + number);
  }

}


