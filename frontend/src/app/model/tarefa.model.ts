export class Tarefa {
    public id: number;
    public titulo: string;
    public descricao: string;
    public dataInicioPrevista: Date;
    public dataTerminoPrevista: Date;
    public dataInicio: Date;
    public dataConclusao: Date;
    public tipo: string;
    public status: string;
    public comentarios: string;
    public tempoPrevisto: Date;
    public tempoGasto: Date;
    public idResponsavel: number;
    public nomeResponsavel: string;
}
