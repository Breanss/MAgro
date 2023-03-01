import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserAuthService} from "./user-auth.service";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FieldService {
  PATH_OF_API = 'http://localhost:8080';

  requestHeader = new HttpHeaders({'No-Auth': 'True'});

  constructor(
    private httpclient: HttpClient
  ) {
  }

  public allFieldUser(): Observable<any> {
    return this.httpclient.get(this.PATH_OF_API + '/field', {responseType:'json'});
  }

  public getUldkItems(data:any): Observable<any> {
    return this.httpclient.get(this.PATH_OF_API + '/field/addfield/uldkitems/'+ data,{responseType:'json'});
  }

  public pdfFieldUser(): Observable<Blob> {
    return this.httpclient.get(this.PATH_OF_API + '/field/pdf',{responseType:'blob'});
  }

  public deleteField(id: any) {
    return this.httpclient.delete(this.PATH_OF_API + '/field/dell/' + id);
  }

  public addField(fieldData: any) {
    return this.httpclient.post(this.PATH_OF_API + '/field/addfield', fieldData);
  }

  public editField(fieldData: any, id: any) {
    return this.httpclient.put(this.PATH_OF_API + '/field/edit/' + id, fieldData);
  }

  public function() {
    const forms = document.querySelectorAll('.needs-validation');

    Array.prototype.slice.call(forms)
      .forEach(function (form) {
        form.addEventListener('submit', function (event: { preventDefault: () => void; stopPropagation: () => void; }) {
          if (!form.checkValidity()) {
            event.preventDefault()
            event.stopPropagation()
          }
          form.classList.add('was-validated')
        }, false)
      })
  }
}
