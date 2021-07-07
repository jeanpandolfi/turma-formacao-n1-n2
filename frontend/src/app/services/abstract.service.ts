import { Table } from 'primeng/table';
import { Observable } from 'rxjs';
import { RequestUtil } from '../util/request-util';
import {environment} from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

export abstract class AbstractService {

    baseUrl;
    http: HttpClient;
    entity;

    constructor(http, entity, servicoUrl) {
        this.http = http;
        this.entity = entity;
        this.baseUrl = `${ environment.apiUrl }`;
    }

    filter(data = {}, tabela: Table, pageable?: {sort: string}): Observable<any> {
        let params = RequestUtil.getRequestParams(tabela);
        if (tabela == null && pageable != null && pageable.sort != null) {
            params = params.append('sort', pageable.sort);
        }
        if (!tabela && !pageable) {
            params = RequestUtil.getHttpParamsComPageAndSize();
        }
        return this.http.post(`${ this.baseUrl }/${ this.entity }/filter`, data, { params: params });
    }

    consultar(data = {}, tabela: Table): Observable<any> {
        return this.http.post(`${this.baseUrl}/${this.entity}/consulta`, data, { params: RequestUtil.getRequestParams(tabela) });
    }

    findById(id: number): any {
        return this.http.get(`${ this.baseUrl }/${ this.entity }/${ id }`);
    }

    save(data): Observable<any> {
        return this.http.post(`${ this.baseUrl }/${ this.entity }`, data);
    }
}
