import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TypecropService {
  PATH_OF_API = 'http://localhost:8080';

  requestHeader = new HttpHeaders({'No-Auth': 'True'});

  constructor(
    private httpclient: HttpClient
  ) {
  }

  public allTypeCrop(): Observable<any> {
    return this.httpclient.get(this.PATH_OF_API + '/crops/typecrop', {responseType:'json'});
  }
}
