import { Component, OnInit } from '@angular/core';
import { AuthService } from '../common/auth.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn$: Observable<boolean>;

  constructor(private authService: AuthService) { }

  onLogout() {
    this.authService.logout();
  }

  ngOnInit() {
    this.isLoggedIn$ = this.authService.isLoggedIn;
    console.log("HeaderComponent Initialized")
  }

}
