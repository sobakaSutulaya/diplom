import {Injectable} from "@angular/core";
import {LocalStorageService} from "./local-storage.service";
import {ApiService} from "./api.service";


@Injectable()
export class WorkspaceService {

  constructor(private localStorageService : LocalStorageService, private apiService : ApiService) {}

  runCode(code : string) {

  }

  submitCode(code : string) {

  }

}
