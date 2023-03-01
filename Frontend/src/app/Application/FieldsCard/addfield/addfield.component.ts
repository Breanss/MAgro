import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {FieldService} from "../../../_services/field.service";

interface uldkItem {
  name: string;
  teryt: string;
}

@Component({
  selector: 'app-addfield',
  templateUrl: './addfield.component.html',
  styles: ['.green {color: darkgreen}'],
  styleUrls: ['./addfield.component.css']
})
export class AddfieldComponent implements OnInit {

  search_types_by_option = {
    Wojewodztwo: {
      param: "GetVoivodeshipById",
      result: "voivodeship",
    },
    Powiat: {
      param: "GetCountyById",
      result: "county",
    },
    Gmina: {
      param: "GetCommuneById",
      result: "commune",
    },
    Region: {
      param: "GetRegionById",
      result: "region",
    },
    Dzialka: {
      param: "GetParcelById",
      result: "geom_wkt",
    },
  };

  message: String = ''
  editForm!: FormGroup;
  itemsWojew!: uldkItem[];
  itemsPowiat!: uldkItem[];
  itemsGmina!: uldkItem[];
  itemsMiejscowosc!: uldkItem[];

  item!:uldkItem;

  constructor(private fieldService: FieldService, private formBuilder: FormBuilder) {
  }

  async ngOnInit(){
    this.editForm = this.formBuilder.group({
      fieldWojewodztwo: '',
      fieldPowiat: '',
      fieldGmina: '',
      fieldMiejscowosc: '',
      fieldProperty: '',
      fieldNumberRegistration: '',
      fieldName: '',
      fieldArea: '',
      fieldArgonomicCategory: '',
      fieldNumber:'',
    })


    this.editForm.controls['fieldProperty'].setValue("Własność");
    this.editForm.controls['fieldArgonomicCategory'].setValue("Bardzo Lekka");
    this.fieldService.function();
    this.editForm.controls['fieldPowiat'].disable();
    this.editForm.controls['fieldGmina'].disable();
    this.editForm.controls['fieldMiejscowosc'].disable();


    this.fieldService.getUldkItems("Wojewodztwo").subscribe(
      (response: any) => {
        this.itemsWojew=response;
      })
  }

  async wojewCheck() {

    this.editForm.controls['fieldPowiat'].enable();
    this.editForm.controls['fieldGmina'].disable();
    this.editForm.controls['fieldMiejscowosc'].disable();
    let tmpp = this.editForm.controls['fieldWojewodztwo'].value;
    let tmp=""
    this.itemsWojew.forEach(x=>{
      if(x.name==tmpp){
        tmp=x.teryt
      }
    });
    this.fieldService.getUldkItems("Powiat="+tmp).subscribe(
      (response: any) => {
        this.itemsPowiat=response;
      })

  }

  async powiatCheck() {
    this.editForm.controls['fieldGmina'].enable();
    this.editForm.controls['fieldMiejscowosc'].disable();
    let tmpp = this.editForm.controls['fieldPowiat'].value;
    let tmp=""
    this.itemsPowiat.forEach(x=>{
      if(x.name==tmpp){
        tmp=x.teryt
      }
    });
    this.fieldService.getUldkItems("Gmina="+tmp).subscribe(
      (response: any) => {
        this.itemsGmina=response;
      })
  }

  async gminaCheck(){
    this.editForm.controls['fieldMiejscowosc'].enable();
    let tmpp = this.editForm.controls['fieldGmina'].value;
    let tmp=""
    this.itemsGmina.forEach(x=>{
      if(x.name==tmpp){
        tmp=x.teryt
      }
    });


    this.fieldService.getUldkItems("Region="+tmp).subscribe(
      (response: any) => {
        this.itemsMiejscowosc=response;
      })

  }

  addFields() {
    this.message = ''
    let tmp = true;

    const forms = document.querySelectorAll('.needs-validation');

    Array.prototype.slice.call(forms)
      .forEach(function (form) {
        if (!form.checkValidity()) {
          tmp = false;
        }

      })
    let i="";

    if(this.itemsMiejscowosc!=null)
    this.itemsMiejscowosc.forEach(x=>{
      if(x.name==this.editForm.controls['fieldMiejscowosc'].value){
        i=x.teryt;
        this.editForm.controls['fieldNumber'].setValue(x.teryt);
      }
    });


    if (tmp) {
      this.fieldService.addField(this.editForm.value).subscribe(
        (response: any) => {
          if (response != null) {
            this.message = "<p>Pomyślnie dodano pole</p>"
            Array.prototype.slice.call(forms)
              .forEach(function (form) {
                form.classList.remove("was-validated");
                form.reset();
              })

          } else {
            this.message = '<p>">Taka nazwa własna pola już istnieje lub dane działki są nieprawidłowe!</p>'
          }
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }

}
