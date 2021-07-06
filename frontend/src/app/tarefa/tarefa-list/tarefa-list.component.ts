import {Component, OnInit, ViewChild} from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {Table} from "primeng/table";
import {Responsavel} from "../../model/responsavel.model";
import {Page} from "../../util/page";
import {PageNotificationService} from "@nuvem/primeng-components";
import {ResponsavelService} from "../../services/responsavel.service";
import {Router} from "@angular/router";
import {MensagemUtil} from "../../util/mensagem-util";
import {finalize} from "rxjs/operators";
import {Tarefa} from "../../model/tarefa.model";
import {TarefaService} from "../../services/tarefa.service";
import {SelectItem} from "primeng";

@Component({
  selector: 'app-tarefa-list',
  templateUrl: './tarefa-list.component.html',
  styleUrls: []
})
export class TarefaListComponent implements OnInit {
    @BlockUI() blockUI: NgBlockUI;
    @ViewChild('tabela') table: Table;

    filtro: Tarefa = new Tarefa();
    tarefaSelecionada: Tarefa = new Tarefa();
    tarefas: Page<Tarefa> = new Page;

    responsaveis: SelectItem[] = [];

    cols = [
        {field: 'titulo', header: 'Nome', sortField: 'nome', text: true},
        {field: 'dataInicioPrevista', header: 'Data Início Prevista', sortField: 'dataInicioPrevista', text: false},
        {field: 'dataTerminoPrevista', header: 'Data Término Prevista', sortField: 'dataTerminoPrevista', text: false},
        {field: 'tipo', header: 'Tipo', sortField: 'tipo', text: true},
        {field: 'status', header: 'Status', sortField: 'status', text: true},
        {field: 'tempoPrevisto', header: 'Tempo Previsto', sortField: 'tempoPrevisto', text: true},
        {field: 'nomeResponsavel', header: 'Responsável', sortField: 'nomeResponsavel', text: true}
    ];

    constructor(private pageNotificationService: PageNotificationService,
                private responsavelService: ResponsavelService,
                private tarefaService: TarefaService,
                private router: Router) { }

    ngOnInit(): void {
        // this.buscarTarefas();
    }

    buscarTarefas(event?: any) {
        if (event) {
            console.log(event);
            localStorage.setItem("RESPONSAVEL", event.data);
        }
        this.filtro.idResponsavel = Number(localStorage.getItem("RESPONSAVEL"));
        this.blockUI.start(MensagemUtil.CARREGANDO);
        this.tarefaService.filter(this.filtro, this.table)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe((res) => {
                console.log(res);
                this.tarefas = res;
            }, (err) => this.pageNotificationService.addErrorMessage("ERRO"));
    }

    buscarResponsaveis() {
        this.blockUI.start(MensagemUtil.CARREGANDO);
        this.responsavelService.buscarDropDown()
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe((res) => {
                console.log(res);
                this.responsaveis = res;
            }, (err) => this.pageNotificationService.addErrorMessage("ERRO"));
    }

    novo() {
        this.router.navigate(['/tarefa']);
    }

}
