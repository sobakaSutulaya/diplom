import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FlexLayoutModule } from '@angular/flex-layout';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { routing } from './app.routing';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule, MatIconModule, MatSidenavModule, MatListModule, MatButtonModule, MatMenuModule, MatTableModule, MatProgressSpinnerModule, MatSelectModule, MatOptionModule, MatSlideToggleModule, MatDividerModule, MatCardModule, MatInputModule } from '@angular/material';
import { AuthService } from './common/auth.service';
import { LocalStorageService } from './common/local-storage.service';
import { HeaderComponent } from './header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    routing,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    MatMenuModule,
    MatTableModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatOptionModule,
    MatSlideToggleModule,
    FlexLayoutModule,
    MatDividerModule,
    MatCardModule,
    MatInputModule
  ],
  providers: [AuthService, LocalStorageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
