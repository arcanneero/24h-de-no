import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BoardComponent } from './pages/board/board.component';
import { AdminComponent } from './pages/admin/admin.component';

const routes: Routes = [
  { path: 'board', component: BoardComponent },
  { path: 'admin', component: AdminComponent },
  { path: '**', redirectTo: 'board', pathMatch: 'full' }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
