import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PatientRoutingModule } from './patient/patient-routing.module';
import { CommonService } from './services/common.service';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, provideHttpClient } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { PatientListComponent } from './patient/patient-list/patient-list.component';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    PatientRoutingModule,
    BrowserModule,
    RouterModule.forRoot([]) ,
    ReactiveFormsModule,
    FormsModule,
    PatientListComponent
    
  ],
  providers: [CommonService,
    provideHttpClient()
  ], 
  bootstrap: []
})
export class AppModule { }
