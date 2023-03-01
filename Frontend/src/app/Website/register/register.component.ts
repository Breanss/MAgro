import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, NgForm} from "@angular/forms";
import {UserService} from "../../_services/user.service";
import {Router} from "@angular/router";
import {FieldService} from "../../_services/field.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  message:String=''
  editForm!:FormGroup;

  constructor(private fieldService:FieldService,private userService:UserService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.editForm=this.formBuilder.group({
      userName:'',
      userEmail:'',
      userTelephonNumber:'',
      userPassword:'',
      userFirstName:'',
      userLastName:''
    })

    this.fieldService.function();
  }

  registerUser() {
    this.message=''
    let tmp = true;
    const forms = document.querySelectorAll('.needs-validation');

    Array.prototype.slice.call(forms)
      .forEach(function (form) {
        if (!form.checkValidity()) {
          tmp=false;
        }

      })

    if(tmp){
      this.userService.register(this.editForm.value).subscribe(
        (response: any) => {
          if(response!=null) {
            this.message="<p>Pomyślnie dodano użytkownika</p>"
            Array.prototype.slice.call(forms)
              .forEach(function (form) {
                form.classList.remove("was-validated");
                form.reset();
              })

          }else {
            this.message = '<p style="color: red">Taka nazwa użytkownika już istnieje</p>'
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
