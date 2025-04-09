import { Routes } from '@angular/router';
import { TestDevComponent } from './test-dev/test-dev.component';
import { MainLayoutComponent } from './shared/layouts/layouts.component';
import { LoginPageComponent } from './auth/login-page/login-page.component';
import { SignUpComponent } from './auth/sign-up/sign-up.component';
import { PatientListComponent } from './patient/patient-list/patient-list.component';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'test-dev',
        pathMatch: 'full', 
    },
    {
        path: '',
        component: MainLayoutComponent,
        children: [
            { path: 'test-dev', component: TestDevComponent },
        ],
    },
    { path: 'login', component: LoginPageComponent },
    { path: 'signup', component: SignUpComponent },
    {
        path: 'patientList', 
        loadChildren: () => import('./patient/patient.module').then(m => m.PatientModule) // Lazy load PatientModule
      }
    
];
