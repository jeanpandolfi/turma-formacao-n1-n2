import {Injectable} from '@angular/core';
import {AbstractService} from "./abstract.service";
import { HttpClient, HttpHeaders, HttpParams, HttpRequest } from '@angular/common/http';
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

    salvar(anexo: File, idTarefa: number): Observable<any> {
        const formData = new FormData();
        formData.append('file', anexo);
        const params = new HttpParams();
        const options = {
            params: params,
            reportProgress: true,
            headers: new HttpHeaders({timeout: `${ 150000 }`})
        };
        return this.http.post(`${this.baseUrl}/${this.entity}/${idTarefa}`, formData, options);
    }

    buscar(id: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/${this.entity}/${id}`);
    }

    buscarAnexoTarefa(idTarefa: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/${this.entity}/tarefa/${idTarefa}`);
    }

    deletar(id: number): Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/${this.entity}/${id}`);
    }

    download(idAnexo: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/${this.entity}/download/${idAnexo}`);
    }
}
