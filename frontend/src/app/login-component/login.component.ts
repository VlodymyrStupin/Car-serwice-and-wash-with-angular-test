import {Component, OnInit} from '@angular/core';
import {LoginService} from "../service/login.service";

@Component({
  selector: 'app-login-component',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email = '';
  password = '';

  constructor(private loginService: LoginService) {
  }

  ngOnInit() {

  }


  login() {
    let payload = {email: this.email, password: this.password};
    this.loginService.login(payload).subscribe((res:any) =>{
      console.log(res) // here I get access token from login process
      this.getAccountPage(res.accessToken) // here I put access token into another method
    })

  }

  getAccountPage(token: string){
    this.loginService.getAccountPage(token).subscribe((res: any) =>{
      console.log(res);
    })
  }
}
