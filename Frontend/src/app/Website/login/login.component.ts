import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from '../../_services/user-auth.service';
import { UserService } from '../../_services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router
  ) {}

  message=''

  ngOnInit(): void {
    if(this.userAuthService.isLoggedIn()!=null){
      this.router.navigate(['/homeapp']);
    }
  }

  login(loginForm: NgForm) {
    this.message='';

    this.userService.login(loginForm.value).subscribe(
      (response: any) => {
        this.userAuthService.setRoles(response.user.role);
        this.userAuthService.setToken(response.jwtToken);

        const role = response.user.role[0].roleName;
        if (role === 'Admin') {
          this.router.navigate(['/admin']);
        } else {
          this.router.navigate(['/homeapp']);
        }
      },
      (error) => {
        const forms = document.querySelectorAll('.needs-validation');
        Array.prototype.slice.call(forms)
          .forEach(function (form) {
            form.reset();
          })
        this.message="Nieprawidłowy login lub hasło!";
      }
    );
  }
}
