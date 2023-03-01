import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-headerapp',
  templateUrl: './headerapp.component.html',
  styleUrls: ['./headerapp.component.css']
})
export class HeaderappComponent implements OnInit {

  public href: string = ""
  public home: string = ""
  public field: string = ""

  constructor(private router: Router) {}

  ngOnInit() {
    this.home=""
    this.field=""
    this.href = this.router.url
    this.activeMenu(this.href)
  }

  private activeMenu(url:String):void{
    switch (url){
      case "/homeapp":
        this.home="home"; break;
      case "/field":
      case "/field/addfield":
        this.field="home"; break;
    }
  }


}
