import { Injectable } from '@angular/core'

@Injectable()
export class LocalStorageService {

    constructor() {
        console.log("Create instance of LocalStorageService: " + Date.now());
    }

    public getItem(key: string): any {
        return JSON.parse(localStorage.getItem(key));
    }

    public setItem(key: string, value: any): void {
        localStorage.setItem(key, JSON.stringify(value));
    }

    public removeItem(key: string): void {
        localStorage.removeItem(key);
    }

    public clear() {
        localStorage.clear();
    }

}