import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CropService {
  PATH_OF_API = 'http://localhost:8080';

  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });
  constructor(
    private httpclient: HttpClient
  ) {}

  public addSeason(seasonData: any) {
    return this.httpclient.post(this.PATH_OF_API + '/seasons/addseason', seasonData);
  }

  public allSeasonUser(): Observable<any> {
    return this.httpclient.get(this.PATH_OF_API + '/seasons', {responseType:'json'});
  }

  public totalAreaCropsForSeason():Observable<any>{
    return this.httpclient.get(this.PATH_OF_API + '/crops/totalarea', {
      responseType: 'json',
    });
  }

  public whetherAllCropsAreDeclaredForSeasons():Observable<any>{
    return this.httpclient.get(this.PATH_OF_API + '/crops/whetheralldeclared', {
      responseType: 'json',
    });
  }
}
