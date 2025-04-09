import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  private baseUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  // Utility method to get the full URL for any endpoint
  getUrl(endpoint: string): string {
    return `${this.baseUrl}${endpoint}`;
  }

  // HTTP GET request
  get(endpoint: string): Observable<any> {
    return this.http.get<any>(this.getUrl(endpoint));
  }

  // HTTP POST request
  post(endpoint: string, data: any): Observable<any> {
    return this.http.post<any>(this.getUrl(endpoint), data);
  }

  // HTTP PUT request
  put(endpoint: string, data: any): Observable<any> {
    return this.http.put<any>(this.getUrl(endpoint), data);
  }

  // HTTP DELETE request
  delete(endpoint: string): Observable<any> {
    return this.http.delete<any>(this.getUrl(endpoint));
  }
}
