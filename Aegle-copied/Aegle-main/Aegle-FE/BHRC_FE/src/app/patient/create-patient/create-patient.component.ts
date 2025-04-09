import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonService } from '../../services/common.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-patient',
  standalone:true,
  imports: [ReactiveFormsModule,CommonModule,FormsModule],
  templateUrl: './create-patient.component.html',
  styleUrl: './create-patient.component.css'
})
export class CreatePatientComponent {
  patient = {
    patientFirstName: '',
    patientLastName: '',
    patientAge: null,
    patientBloodGroup: '',
    phoneNumber: '',
    patientEmail: '',
    patientAddress: '',
    pincode: ''
  };
  successMessage:String =""
  errorMessage:String=""
  constructor(private fb: FormBuilder,private commonService: CommonService,private router: Router) {
  }

  ngOnInit(): void {
    
  }

  // Getter to easily access form controls in the template
  
  // Submit form and handle form submission
  onSubmit(): void {
    if (this.isFormValid()) {
      this.commonService.post('patientController/createOrUpdatePatient',this.patient).subscribe(
        (response) => {
          this.successMessage = 'Patient added successfully!';
          this.errorMessage = '';
          this.router.navigate(['/patientList']);
        },
        (error) => {
          this.errorMessage = 'Failed to add patient. Please try again.';
          this.successMessage = '';
        }
      );
    }


    
  }
  isFormValid() {
     return this.patient.patientFirstName && this.patient.patientLastName && this.patient.patientAge && this.patient.patientEmail;
  }
}
