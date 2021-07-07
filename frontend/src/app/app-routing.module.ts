import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { LoginSuccessComponent } from '@nuvem/angular-base';
import { ResponsavelFormComponent } from './reponsavel/responsavel-form/responsavel-form.component';
import { ResponsavelListComponent } from './reponsavel/responsavel-list/responsavel-list.component';
import { TarefaListComponent } from './tarefa/tarefa-list/tarefa-list.component';
import { TarefaFormComponent } from './tarefa/tarefa-form/tarefa-form.component';

const routes: Routes = [
    { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros'} },
    { path: 'login-success', component: LoginSuccessComponent },
    {
        path: 'responsaveis',
        component: ResponsavelListComponent,
        data: { breadcrumb: 'Responsável'}
    },
    {
        path: 'responsavel',
        component: ResponsavelFormComponent,
        data: { breadcrumb: 'Responsável'},
    },
    {
        path: 'responsavel/:id',
        component: ResponsavelFormComponent,
    },
    {
        path: 'tarefas',
        component: TarefaListComponent,
        data: { breadcrumb: 'Tarefas'}
    },
    {
        path: 'tarefa',
        component: TarefaFormComponent,
        data: { breadcrumb: 'Tarefa'},
        children: [
            {
                path: ':id',
                component: TarefaFormComponent
            }
        ]
    }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
