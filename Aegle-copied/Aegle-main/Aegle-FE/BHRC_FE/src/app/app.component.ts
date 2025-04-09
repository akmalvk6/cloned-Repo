import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { MainLayoutComponent } from './shared/layouts/layouts.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  

})
export class AppComponent {
  
}
