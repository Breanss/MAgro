import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UserAuthService} from "../../../_services/user-auth.service";
import {UserService} from "../../../_services/user.service";

@Component({
  selector: 'app-headerapp',
  templateUrl: './headerapp.component.html',
  styleUrls: ['./headerapp.component.css']
})
export class HeaderappComponent implements OnInit {

  public href: string = "";
  public home: string = "";
  public field: string = "";
  public open: string = "";
  public crop: string = "";
  public username: string = "";
  public treatment: string = "";
  public finance: string = "";
  public yield: string = "";

  constructor(private router: Router,  private userAuthService: UserAuthService, private userService:UserService) {
  }

  ngOnInit() {
    this.home = "";
    this.field = "";
    this.crop = "";
    this.treatment = "";
    this.finance = "";
    this.yield = "";
    this.open = "";

    this.href = this.router.url;
    this.activeMenu(this.href);

      this.userService.loginUsername().subscribe(
      (response)=>{
        this.username=response;
      },(error => {
          this.userAuthService.clear();
        })
      )
  }

  private activeMenu(url: String): void {
    const tab = url.split("/");

    switch (url) {
      case "/homeapp":
        this.home = "active";
        break;
      case "/field":
      case "/field/addfield":
        this.field = "active";
        break;
      case "/crops":
      case "/crops/" + tab[2] + "/setcrops":
        this.crop = "active";
        break;
      case "/treatments":
        this.treatment = "active";
        break;
      case "/finances":
        this.finance = "active";
        break;
      case "/yields":
        this.yield = "active";
        break;
    }
  }

  public logout() {
    this.userAuthService.clear();
    this.router.navigate(['/']);
  }


  public togller(){
    if(this.open=="open"){
      this.open="show";
    }else{
      this.open="open"
    }

  }
}
