import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import {WorkspaceComponent} from "./workspace/workspace.component";
import {EventWorkspaceComponent} from "./event-workspace/event-workspace.component";


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'workspace', component: WorkspaceComponent},
  { path : 'event-workspace', component: EventWorkspaceComponent}
];

export const routing = RouterModule.forRoot(routes);
