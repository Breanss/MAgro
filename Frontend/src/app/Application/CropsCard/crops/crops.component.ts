import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {FieldService} from "../../../_services/field.service";
import {CropService} from "../../../_services/crop.service";

interface seasonItem {
  seasonId: any
  year: bigint;
}

@Component({
  selector: 'app-crops',
  templateUrl: './crops.component.html',
  styleUrls: ['./crops.component.css']
})
export class CropsComponent implements OnInit {
  public addSeasonForm!: FormGroup;
  public itemsSeason!: seasonItem[];
  public year:any;
  public area:any;
  public whetherDeclared:any;

  constructor(private cropsService: CropService, private formBuilder: FormBuilder) {
    this.totalArea()
    this.whetherAllDeclared();
    this.allSeason();
  }

  ngOnInit(): void {
    this.addSeasonForm = this.formBuilder.group({
      year: '',
    })

  }

  public addSeason() {
    this.cropsService.addSeason(this.addSeasonForm.value).subscribe(
      (response: any) => {
          this.allSeason();
          this.totalArea();
          this.whetherAllDeclared();
      },(error) => {
          console.log(error);
      });
  }


  public allSeason(){
    this.cropsService.allSeasonUser().subscribe(
      (response) => {
        console.log(response)
        this.itemsSeason=response;
      },
      (error)=>{
        console.log(error);
      }
    );
  }

  public totalArea(){
    this.cropsService.totalAreaCropsForSeason().subscribe(
      (response) => {
        console.log(response)
         this.area=response;
      },
      (error)=>{
        console.log(error);
      }
    );
  }

  public whetherAllDeclared(){
    this.cropsService.whetherAllCropsAreDeclaredForSeasons().subscribe(
      (response) => {
        console.log(response)
        this.whetherDeclared=response;
      },
      (error)=>{
        console.log(error);
      }
    );
  }
}
