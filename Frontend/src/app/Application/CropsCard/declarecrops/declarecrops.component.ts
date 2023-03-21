import {Component, Input, OnInit} from '@angular/core';
import {CropService} from "../../../_services/crop.service";
import {Router} from "@angular/router";
import {TypecropService} from "../../../_services/typecrop.service";
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Browser} from "leaflet";
import android = Browser.android;

@Component({
  selector: 'app-declarecrops',
  templateUrl: './declarecrops.component.html',
  styleUrls: ['./declarecrops.component.css']
})
export class DeclarecropsComponent implements OnInit {

  public seasonData:any;
  public cropsData:any;
  public rok:any;
  public itemsType:any;
  public setCropForm!: FormGroup;

  public displayedColumns: string[] = ['name','number','area','crop', 'variety'];

  constructor(private cropsService: CropService,private typecropService:TypecropService, private router: Router, private formBuilder: FormBuilder) {
    this.setCropForm = this.formBuilder.group({
      crops: this.formBuilder.array([]) ,
    });


  }


  ngOnInit(): void {
    this.getAllTypeCrops();
    this.getSeason(this.router.url.split("/")[2]);
  }

  public getSeason(id:any){
    this.cropsService.seasonById(id).subscribe(
      (response: any) => {
        this.seasonData=response;
        this.rok=this.seasonData.year;
        this.getAllCropsSeason(this.seasonData.seasonId);
      }, (error) => {
        console.log(error);
      });
  }



  public getAllCropsSeason(id:any){

    let tab: { name: any; variety: any; }[] = [];
    let i = 0;

    this.cropsService.allCropSeason(id).subscribe(
      (response: any) => {
        this.cropsData=response;
        this.cropsData.forEach((x:any)=>{
          (<FormArray>this.setCropForm.get('crops')).push(this.formBuilder.group({
            name:'',
            variety:'',
          }));
          console.log(x)
          tab.push({name:x.typeCrop.name,variety:x.variety})
        })



        this.setCropForm.get('crops')?.setValue(tab)

      }, (error) => {
        console.log(error);
      });
  }

  public getAllTypeCrops(){
    this.typecropService.allTypeCrop().subscribe(
      (response:any)=>{
        this.itemsType=response;
      },(error)=>{
        console.log(error);
      });
  }
}
