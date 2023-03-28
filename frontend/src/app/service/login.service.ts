import {Injectable} from '@angular/core';
import {Component, OnInit} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  email = '';
  password = '';
  private header = new HttpHeaders();

  constructor(private httpClient: HttpClient) {

  }

  login(payload: any) {
    return this.httpClient.post("http://localhost:80/rest/login", payload)
  }

  getAccountPage(token: string) {
    this.header = this.header.set('Authorization', 'Bearer ' + token)
    return this.httpClient.get("http://localhost:8080/rest/hello", {'headers': this.header})
  }
}
