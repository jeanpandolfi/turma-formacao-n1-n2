import {Injectable} from '@angular/core';
import {AbstractService} from "./abstract.service";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AnexoService extends AbstractService {

    constructor(http: HttpClient) {
        super(http, 'anexo', environment.apiUrl);
    }

    listar(): Observable<any> {
        return this.http.get(`${this.baseUrl}/${this.entity}`);
    }

    salvar(anexo: any): Observable<any> {
        return this.http.post(`${this.baseUrl}/${this.entity}`, anexo);
    }

    buscar(id: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/${this.entity}/${id}`);
    }

}
