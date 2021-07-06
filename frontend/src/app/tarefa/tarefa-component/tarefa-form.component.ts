import {Component, OnInit, ViewChild} from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {PageNotificationService} from "@nuvem/primeng-components";
import {ActivatedRoute, Router} from "@angular/router";
import {MensagemUtil} from "../../util/mensagem-util";
import {finalize} from "rxjs/operators";
import {Tarefa} from "../../model/tarefa.model";
import {TarefaService} from "../../services/tarefa.service";
import {FileUpload, SelectItem} from "primeng";
import {AnexoService} from "../../services/anexo.service";
import {ResponsavelService} from "../../services/responsavel.service";

@Component({
  selector: 'app-tarefa-component',
  templateUrl: './tarefa-form.component.html',
  styleUrls: []
})
export class TarefaFormComponent implements OnInit {

    @BlockUI() blockUI: NgBlockUI;
    @ViewChild('fileUpload') fileUpload: FileUpload;

    formTarefa: FormGroup;
    tarefa: Tarefa = new Tarefa();
    anexo: any;
    anexos: any[] = [];
    visualizar = false;

    responsaveis: SelectItem[] = [];
    tiposArquivosPermitidos = [
        'application/pdf',
        'image/jpeg',
        'image/png',
    ];

    constructor(private fb: FormBuilder,
                private pageNotificationService: PageNotificationService,
                private tarefaService: TarefaService,
                private anexoService: AnexoService,
                private responsavelService: ResponsavelService,
                private router: Router,
                private activatedRoute: ActivatedRoute) { }

    ngOnInit(): void {
        this.montarFormulario();
        // this.verificarAcao();
    }

    verificarAcao() {
        this.activatedRoute.paramMap.subscribe(params => {
            if (params['id']) {
                this.buscarTarefa(params['id']);
            }
            if (params['acao']) {
                this.visualizar = true;
            }
        });
    }

    montarFormulario() {
        this.formTarefa = this.fb.group({
            'titulo': new FormControl('', [Validators.required]),
            'descricao': new FormControl('', [Validators.required]),
            'dataInicioPrevista': new FormControl(null, [Validators.required]),
            'dataTerminoPrevista': new FormControl(null, [Validators.required]),
            'dataInicio': new FormControl(null),
            'dataConclusao': new FormControl(null),
            'tipo': new FormControl('', [Validators.required]),
            'status': new FormControl('AGUARDANDO INÍCIO', [Validators.required]),
            'tempoPrevisto': new FormControl(null, [Validators.required]),
            'tempoGasto': new FormControl(null),
        });
    }

    salvar(formResponsavel: FormGroup) {
        if (!formResponsavel.valid) {
            this.pageNotificationService.addErrorMessage(MensagemUtil.PREENCHA_TODOS_CAMPOS);
            return;
        }
        this.tarefa = formResponsavel.getRawValue();
        this.tarefa.idResponsavel = 1;
        this.blockUI.start(MensagemUtil.CARREGANDO);
        this.tarefaService.salvar(this.tarefa)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe((res: Tarefa) => {
                // this.router.navigateByUrl('/tarefa' + res.id);
                this.pageNotificationService.addCreateMsg(MensagemUtil.CADASTRO_REALIZADO_SUCESSO);
            }, (err) => this.pageNotificationService.addErrorMessage("ERRO"));
    }

    private buscarTarefa(id: number) {
        this.blockUI.start(MensagemUtil.CARREGANDO);
        this.tarefaService.buscar(id)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe((res: Tarefa) => {
                console.log(res);
                // this.formResponsavel.reset(res);
            }, (err) => this.pageNotificationService.addErrorMessage("ERRO"));
    }

    enviarAnexo(evento) {
        const anexo = evento.files[0];
        if (!this.tiposArquivosPermitidos.some(type => type === anexo.type)) {
            this.pageNotificationService.addErrorMessage('São permitidos apenas os formatos: ".pdf", ".png", ".jpeg", ".jpg"');
            this.fileUpload.clear();
            return;
        }
        this.blockUI.start();
        console.log(anexo);
        this.anexoService.salvar(anexo)
            .pipe(finalize(() => {
                this.blockUI.stop();
            }))
            .subscribe(
                (res) => {
                    // if (res.body) {
                    //     this.detalheDocumentoProposicao.arquivoUpload = res.body;
                    //     this.arquivos = [res.body];
                    // }
                },
                (err) => {
                    this.pageNotificationService.addErrorMessage(err.error.detail);
                }
            );

    }

    removerAnexo() {
        this.fileUpload.clear();
        // this.detalheDocumentoProposicao.arquivoUpload = null;
        // this.arquivos = [];
    }

}
