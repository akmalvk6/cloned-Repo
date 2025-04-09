import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonService } from '../../services/common.service';
import { SearchDTO } from '../../models/searchDTO';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { HeaderComponent } from "../../shared/header/header.component";

@Component({
  selector: 'app-patient-list',
  standalone: true,
  imports: [CommonModule, FormsModule, NgxPaginationModule, HeaderComponent],
  templateUrl: './patient-list.component.html',
  styleUrl: './patient-list.component.css',
 
})
export class PatientListComponent {
  patients: any[] = [];  // To hold the patient data
  errorMessage: string = '';
  url:String  = ''; // For error messages
  searchDTO: SearchDTO = new SearchDTO(); // Instance of SearchCriteria
  responseMessage = "";
  searchQuery = ''; 
  p: number = 1;
  pageSize: number = 10;
  totalItems: number = 0;

  constructor(private commonServices: CommonService,private router: Router) { }

  ngOnInit(): void {
    this.listPatients()
  }

  listPatients(): void {
    if(this.searchQuery != null){
      this.searchDTO.keyword = this.searchQuery;
    }
    this.searchDTO.pageSize = this.pageSize,
    this.searchDTO.start = (this.p - 1) * this.pageSize; // Calculate the start index
    this.commonServices.post('patientController/listPatients', this.searchDTO).subscribe(
      (data) => {
        this.patients = data.data.data;  
        this.totalItems = data.data.count;
      },
      (error) => {
        this.errorMessage = 'Failed to fetch patient data!';  
        console.error('Error fetching patients:', error); 
      }
    );
  }
  addNewPatient():void{
    this.router.navigate(['patientList/addPatient']);
  }
  deletePatient(patientId: string){
    this.commonServices.delete('patientController/patient/'+patientId).subscribe(
      (data) => {
        this.responseMessage = data.message;
        this.listPatients()
      },
      (error) => {
        this.errorMessage = 'Failed to delete';  
      }
    );
  }
  editPatient(patientId: string){

  }
  pageChange(event: number): void {
    console.log(event);
    this.p = event;
    this.listPatients();
  }
  
}
