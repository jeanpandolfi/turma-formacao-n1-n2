import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {AbstractService} from "./abstract.service";
import {Responsavel} from "../model/responsavel.model";
import {SelectItem} from "primeng";

@Injectable({
  providedIn: 'root'
})
export class ResponsavelService extends AbstractService {

    constructor(http: HttpClient) {
      super(http, 'responsavel', environment.apiUrl);
    }

    buscarDropDown(): Observable<SelectItem[]> {
        return this.http.get<SelectItem[]>(`${this.baseUrl}/${this.entity}/dropdown`);
    }

    salvar(responsavel: Responsavel): Observable<Responsavel> {
        return this.http.post<Responsavel>(`${this.baseUrl}/${this.entity}`, responsavel);
    }

    buscar(id: number): Observable<Responsavel> {
        return this.http.get<Responsavel>(`${this.baseUrl}/${this.entity}/${id}`);
    }
}
