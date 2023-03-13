import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {FieldService} from "../../../_services/field.service";
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {DialogeditComponent} from "../dialogedit/dialogedit.component";


import {MapComponent} from "../map/map.component";



@Component({
  selector: 'app-fields',
  templateUrl: './fields.component.html',
  styleUrls: ['./fields.component.css']
})

export class FieldsComponent implements OnInit{


  @ViewChild("MapComponent") component?: MapComponent;

  public dataSource = [{ numberRegistration:String, name:String, area:String, argonomicCategory:String, property:String, fieldId:Number}];

  constructor(private fieldService: FieldService, public dialog: MatDialog) {}
  displayedColumns: string[] = ['name', 'numberRegistration', 'area', 'argonomicCategory', 'property', 'fieldAction'];
  ngOnInit(): void {
    this.allField();
  }

  openDialog(row:any) {
    this.dialog.open(DialogeditComponent, {
      data: row
    });
  }


  pdfFieldUser(){
    this.fieldService.pdfFieldUser().subscribe(x=>{
      const blob = new Blob([x], {type:'application/pdf'});
      const data = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href=data;
      link.download="pola.pdf";
      link.dispatchEvent(new MouseEvent('click', {bubbles:true, cancelable:true, view:window}));
      setTimeout(function (){
        window.URL.revokeObjectURL(data);
        link.remove();
      },100)
    })

  }

  deleteField(id:number){
    this.fieldService.deleteField(id).subscribe(
      (response) => {
        this.allField();
      },
      (error)=>{
        console.log(error);
      }
    );
  }


  allField() {
    this.fieldService.allFieldUser().subscribe(
      (response) => {
        console.log(response)
        this.dataSource=response
      },
      (error)=>{
        console.log(error);
      }
    );
  }


  plotMap(teryt: string, number:String){
    this.component?.getParcelById(teryt+'.'+number);
  }

}


