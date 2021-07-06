import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { PageNotificationService } from '@nuvem/primeng-components';
import { MensagemUtil } from '../../util/mensagem-util';
import { Responsavel } from '../../model/responsavel.model';
import { ResponsavelService } from '../../services/responsavel.service';
import { finalize } from 'rxjs/operators';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { ActivatedRoute, Router } from '@angular/router';
import { DateUtil } from '../../util/date-util';

@Component({
  selector: 'app-responsavel-form',
  templateUrl: './responsavel-form.component.html',
  styleUrls: []
})
export class ResponsavelFormComponent implements OnInit {
    @BlockUI() blockUI: NgBlockUI;
    formResponsavel: FormGroup;
    responsavel: Responsavel = new Responsavel();
    editar = false;

    dataBr = DateUtil.dataBr;

    constructor(private fb: FormBuilder,
                private pageNotificationService: PageNotificationService,
                private responsavelService: ResponsavelService,
                private router: Router,
                private activatedRoute: ActivatedRoute) { }

    ngOnInit(): void {
       this.montarFormulario();
        this.verificarEdicao();
    }

    verificarEdicao() {
        if (this.activatedRoute.snapshot.paramMap.get('id')) {
            this.editar = true;
            this.buscarResponsavel(Number(this.activatedRoute.snapshot.paramMap.get('id')));
        }
    }

    montarFormulario() {
        this.formResponsavel = this.fb.group({
            'id': new FormControl(null),
            'nome': new FormControl('', [Validators.required]),
            'email': new FormControl('', [Validators.required, Validators.maxLength(200), Validators.email]),
            'dataNascimento': new FormControl('', [Validators.required]),
            'status': new FormControl('ATIVO', [Validators.required]),
        });
    }

    salvar(formResponsavel: FormGroup) {
        if (!formResponsavel.valid) {
            this.pageNotificationService.addErrorMessage(MensagemUtil.PREENCHA_TODOS_CAMPOS);
            return;
        }
        this.responsavel = formResponsavel.getRawValue();
        this.blockUI.start(MensagemUtil.CARREGANDO);
        this.responsavelService.salvar(this.responsavel)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe((res: Responsavel) => {
                this.router.navigateByUrl('/responsaveis');
                this.pageNotificationService.addCreateMsg();
            }, (err) => this.pageNotificationService.addErrorMessage('ERRO'));
    }

    private buscarResponsavel(id: number) {
        this.blockUI.start(MensagemUtil.CARREGANDO);
        this.responsavelService.buscar(id)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe((res: Responsavel) => {
                res.dataNascimento = new Date(res.dataNascimento);
                this.responsavel = res;
                this.formResponsavel.reset(res);
            }, (err) => this.pageNotificationService.addErrorMessage('ERRO'));
    }
}
