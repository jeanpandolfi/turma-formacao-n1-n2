import { Component, OnInit, ViewChild } from '@angular/core';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { Table } from 'primeng/table';
import { Page } from '../../util/page';
import { PageNotificationService } from '@nuvem/primeng-components';
import { ResponsavelService } from '../../services/responsavel.service';
import { Router } from '@angular/router';
import { MensagemUtil } from '../../util/mensagem-util';
import { finalize } from 'rxjs/operators';
import { Tarefa } from '../../model/tarefa.model';
import { TarefaService } from '../../services/tarefa.service';
import { SelectItem } from 'primeng';
import { TarefaList } from '../../model/tarefa-list.model';
import { TarefaFiltro } from '../../model/filtro/tarefa-filtro.model';

@Component({
  selector: 'app-tarefa-list',
  templateUrl: './tarefa-list.component.html',
  styleUrls: []
})
export class TarefaListComponent implements OnInit {
    @BlockUI() blockUI: NgBlockUI;
    @ViewChild('tabela') table: Table;

    filtro: TarefaFiltro = new TarefaFiltro();
    tarefaSelecionada: Tarefa = new Tarefa();
    tarefas: Page<TarefaList> = new Page;

    responsaveis: SelectItem[] = [];

    cols = [
        {field: 'titulo', header: 'Título', sortField: 'titulo.keyword', text: true},
        {field: 'dataInicioPrevista', header: 'Data Início Prevista', sortField: 'dataInicioPrevista', text: false},
        {field: 'dataTerminoPrevista', header: 'Data Término Prevista', sortField: 'dataTerminoPrevista', text: false},
        {field: 'tipo', header: 'Tipo', sortField: 'tipo.keyword', text: true},
        {field: 'status', header: 'Status', sortField: 'status.keyword', text: true},
        {field: 'tempoPrevisto', header: 'Tempo Previsto', sortField: 'tempoPrevisto', text: true},
        {field: 'responsavelNome', header: 'Responsável', sortField: 'responsavelNome.keyword', text: true}
    ];

    constructor(private pageNotificationService: PageNotificationService,
                private responsavelService: ResponsavelService,
                private tarefaService: TarefaService,
                private router: Router) { }

    ngOnInit(): void {
        this.buscarTarefas();
        this.buscarResponsaveis();
    }

    buscarTarefas(event?: any) {
        if (event) {
            localStorage.setItem("RESPONSAVEL", event.value);
        }
        this.filtro.responsavelId = Number(localStorage.getItem("RESPONSAVEL")) ? Number(localStorage.getItem("RESPONSAVEL")) : null;
        this.blockUI.start(MensagemUtil.CARREGANDO);
        this.tarefaService.filter(this.filtro, this.table)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe((res) => {
                this.tarefas = res;
            }, (err) => this.pageNotificationService.addErrorMessage("ERRO"));
    }

    buscarResponsaveis() {
        this.blockUI.start(MensagemUtil.CARREGANDO);
        this.responsavelService.buscarDropDown()
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe((res) => {
                res.unshift({value: 0, label: 'Todos'});
                this.responsaveis = res;
            }, (err) => this.pageNotificationService.addErrorMessage("ERRO"));
    }

    novo() {
        this.router.navigate(['/tarefa']);
    }

}
