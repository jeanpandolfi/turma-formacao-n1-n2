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
    editar = false;

    responsaveis: SelectItem[] = [];

    constructor(private fb: FormBuilder,
                private pageNotificationService: PageNotificationService,
                private tarefaService: TarefaService,
                private anexoService: AnexoService,
                private responsavelService: ResponsavelService,
                private router: Router,
                private activatedRoute: ActivatedRoute) { }

    ngOnInit(): void {
        this.montarFormulario();
        this.verificarAcao();
    }

    verificarAcao() {
        if (this.activatedRoute.snapshot.paramMap.get('id')) {
            this.editar = true;
            const id = Number(this.activatedRoute.snapshot.paramMap.get('id'));
            this.buscarTarefa(id);
            this.buscarAnexosTarefa(id);
        }
    }

    montarFormulario() {
        this.formTarefa = this.fb.group({
            'id': new FormControl(null),
            'titulo': new FormControl('', [Validators.required]),
            'descricao': new FormControl('', [Validators.required]),
            'dataInicioPrevista': new FormControl(null, [Validators.required]),
            'dataTerminoPrevista': new FormControl(null, [Validators.required]),
            'dataInicio': new FormControl(null),
            'dataConclusao': new FormControl(null),
            'tipo': new FormControl('', [Validators.required]),
            'status': new FormControl('', [Validators.required]),
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
        this.tarefa.idResponsavel = Number(localStorage.getItem('RESPONSAVEL'));
        this.blockUI.start(MensagemUtil.CARREGANDO);
        this.tarefaService.salvar(this.tarefa)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe((res: Tarefa) => {
                this.router.navigateByUrl('/tarefa/' + res.id);
                this.pageNotificationService.addCreateMsg(MensagemUtil.CADASTRO_REALIZADO_SUCESSO);
            }, (err) => this.pageNotificationService.addErrorMessage("ERRO"));
    }

    private buscarTarefa(id: number) {
        this.blockUI.start(MensagemUtil.CARREGANDO);
        this.tarefaService.buscar(id)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe((res: Tarefa) => {
                res.dataInicioPrevista = new Date(res.dataInicioPrevista);
                res.dataTerminoPrevista = new Date(res.dataTerminoPrevista);
                res.dataConclusao = new Date(res.dataConclusao);
                res.dataInicio = new Date(res.dataInicio);
                res.tempoGasto = new Date(res.tempoGasto);
                res.tempoPrevisto = new Date(res.tempoPrevisto);
                this.tarefa = res;
                this.formTarefa.reset(res);
            }, (err) => this.pageNotificationService.addErrorMessage("ERRO"));
    }

    enviarAnexo(evento) {
        if (!this.tarefa.idResponsavel) {
            this.pageNotificationService.addInfoMessage('Crie a tarefa antes de anexar um arquivo');
            return;
        }
        const anexo = evento.files[0];
        this.blockUI.start();
        this.anexoService.salvar(anexo, this.tarefa.id)
            .pipe(finalize(() => {
                this.blockUI.stop();
                this.fileUpload.clear();
            }))
            .subscribe(
                (res) => this.anexos = res,
                (err) => this.pageNotificationService.addErrorMessage(null,"Erro ao enviar o arquivo")
            );

    }

    removerAnexo(id: number) {
        this.blockUI.start(MensagemUtil.CARREGANDO);
        this.anexoService.deletar(id)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe((res) => {
                    this.pageNotificationService.addSuccessMessage('Removido!');
                    this.buscarAnexosTarefa(this.tarefa.id);
                    this.fileUpload.clear();
                },
                (err) => this.pageNotificationService.addErrorMessage("ERRO"));
    }

    private buscarAnexosTarefa(id: number) {
        this.blockUI.start(MensagemUtil.CARREGANDO);
        this.anexoService.buscarAnexoTarefa(id)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe((res) => this.anexos = res,
                (err) => this.pageNotificationService.addErrorMessage("ERRO"));
    }

    download(idAnexo: number) {
        this.blockUI.start(MensagemUtil.CARREGANDO);
        this.anexoService.download(idAnexo)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe((res) => {
                    console.log(res);
                    this.pageNotificationService.addSuccessMessage('Removido!');
                    this.buscarAnexosTarefa(this.tarefa.id);
                    this.fileUpload.clear();
                },
                (err) => this.pageNotificationService.addErrorMessage("ERRO"));
    }
}
