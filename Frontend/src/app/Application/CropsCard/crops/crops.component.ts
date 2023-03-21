import {Component, Injectable, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
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
  public year: any;
  public area: any;
  public whetherDeclared!: any;

  constructor(private cropsService: CropService, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.addSeasonForm = this.formBuilder.group({
      year: '',
    })

    this.addSeasonForm.controls['year'].setValue("2023");

    this.totalArea();
    this.whetherAllDeclared();
    this.allSeason();
  }

  public addSeason() {
    this.cropsService.addSeason(this.addSeasonForm.value).subscribe(
      (response: any) => {
        this.totalArea();
        this.whetherAllDeclared();
        this.allSeason();
      }, (error) => {
        console.log(error);
      });
  }


  public allSeason() {
    this.cropsService.allSeasonUser().subscribe(
      (response) => {
        this.itemsSeason = response;
        if (this.area == undefined) {
          this.area = new Array(this.itemsSeason.length);
        }
        if (this.whetherDeclared == undefined) {
          this.whetherDeclared = new Array(this.itemsSeason.length);
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }

  public totalArea() {
    this.cropsService.totalAreaCropsForSeason().subscribe(
      (response) => {
        this.area = response;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  public whetherAllDeclared() {
    this.cropsService.whetherAllCropsAreDeclaredForSeasons().subscribe(
      (response) => {
        this.whetherDeclared = response;
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
