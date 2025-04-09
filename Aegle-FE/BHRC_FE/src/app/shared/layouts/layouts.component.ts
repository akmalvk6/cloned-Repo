import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-main-layout',
  imports: [RouterModule, HeaderComponent],
  templateUrl: './layouts.component.html',
  styleUrls: ['./layouts.component.css']
})
export class MainLayoutComponent {}
