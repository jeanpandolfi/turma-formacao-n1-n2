import {Component, OnInit, ViewChild} from '@angular/core';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {PageNotificationService} from '@nuvem/primeng-components';
import {ResponsavelService} from '../../services/responsavel.service';
import {MensagemUtil} from '../../util/mensagem-util';
import {finalize} from 'rxjs/operators';
import {Responsavel} from '../../model/responsavel.model';
import {Page} from '../../util/page';
import {Table} from 'primeng/table';
import {Router} from '@angular/router';

@Component({
  selector: 'app-responsavel-list',
  templateUrl: './responsavel-list.component.html',
  styleUrls: []
})
export class ResponsavelListComponent implements OnInit {
    @BlockUI() blockUI: NgBlockUI;
    @ViewChild('tabela') table: Table;

    filtro: Responsavel = new Responsavel();
    responsavelSelecionado: Responsavel = new Responsavel();
    responsaveis: Page<Responsavel> = new Page;

    cols = [
        {field: 'nome', header: 'Nome', sortField: 'nome', text: true},
        {field: 'email', header: 'Email', sortField: 'email', text: true},
        {field: 'status', header: 'Status', sortField: 'status', text: true}
    ];

    constructor(private pageNotificationService: PageNotificationService,
                private responsavelService: ResponsavelService,
                private router: Router) { }

    ngOnInit(): void {
        this.buscarResponsaveis();
    }

    buscarResponsaveis() {
        this.blockUI.start(MensagemUtil.CARREGANDO);
        this.responsavelService.filter(this.filtro, this.table)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe((res) => {
                console.log(res)
                this.responsaveis = res;
            }, (err) => this.pageNotificationService.addErrorMessage("ERRO"));
    }

    novo() {
        this.router.navigate(['/responsavel']);
    }

    editar() {
        this.router.navigateByUrl('/responsavel/' + this.responsavelSelecionado.id);
        // this.router.navigate(['/responsavel', this.responsavelSelecionado.id]);
    }
}
