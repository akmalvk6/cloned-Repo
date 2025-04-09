import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatientListComponent } from './patient-list/patient-list.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CreatePatientComponent } from './create-patient/create-patient.component';

const routes: Routes = [
  { path: '', component: PatientListComponent }, 
  { path: 'addPatient', component: CreatePatientComponent }, 
  
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),

  ],
  exports: [RouterModule]
})
export class PatientRoutingModule { }
