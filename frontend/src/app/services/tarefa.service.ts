import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Tarefa } from '../model/tarefa.model';

@Injectable({
  providedIn: 'root'
})
export class TarefaService extends AbstractService {

    constructor(http: HttpClient) {
        super(http, 'tarefa', environment.apiUrl);
    }

    listar(): Observable<any> {
        return this.http.get(`${this.baseUrl}/${this.entity}`);
    }

    salvar(tarefa: Tarefa): Observable<Tarefa> {
        return this.http.post<Tarefa>(`${this.baseUrl}/${this.entity}`, tarefa);
    }

    buscar(id: number): Observable<Tarefa> {
        return this.http.get<Tarefa>(`${this.baseUrl}/${this.entity}/${id}`);
    }
}
