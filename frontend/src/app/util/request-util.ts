import { HttpParams } from '@angular/common/http';
import { Table } from 'primeng/table';

export class RequestUtil {
    static getHttpParamsComPageAndSize(page: number = 0 , size: number = 20): any {
        let params: HttpParams = new HttpParams();

        params = params.append('page', String(page));
        params = params.append('size', String(size));
        params = params.append('sort', '');
        return params;
    }

    static getRequestParams(table: Table): HttpParams {
        let params: HttpParams = new HttpParams();
        if (table == null) {
            return params;
        }
        params = params.append('page', Math.round(table.first / table.rows).toString());
        params = params.append('size', table.rows == null ? null : table.rows.toString());

        const direction = table.sortOrder === 1 ? 'ASC' : 'DESC';
        params = params.append('sort', table.sortField == null ? '' : table.sortField + ',' + direction);
        return params;
    }

    static objetoForHttpParams(objetoIgualHttpParams: any) {
        const params: HttpParams = new HttpParams();
        const page = objetoIgualHttpParams.updates.find(u => u.param === "page").value;
        const size = objetoIgualHttpParams.updates.find(u => u.param === "size").value;
        const sort = objetoIgualHttpParams.updates.find(u => u.param === "sort").value;

        return params.set('page', page).set('size', size).set("sort", sort);
    }

    static mapSelectItem(data: any[]) {
        return data.map(({id, nome}) => ({label: nome, value: id}));
    }
}
