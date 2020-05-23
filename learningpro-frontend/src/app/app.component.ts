import { Component, OnDestroy, OnInit } from '@angular/core';
import { LocalStorageService } from './common/local-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  template: `<app-header></app-header>
  <router-outlet></router-outlet>`,
  // templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnDestroy, OnInit {
  title = 'learningpro-frontend';

  constructor(private localStorageService: LocalStorageService, private router: Router) { }

  ngOnInit() {
    // this.router.navigate(['/login']);
  }

  ngOnDestroy() {
    // this.localStorageService.clear();
  }
}
