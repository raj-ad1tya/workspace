import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Bus } from '../models/bus.model';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private apiUrl = 'http://localhost:8080/api/bus';

  constructor(private http: HttpClient) { }

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token') || '';
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  addBus(requestObject: FormData): Observable<Bus> {
    return this.http.post<Bus>(this.apiUrl, requestObject, {
      headers: this.getAuthHeaders()
    });
  }

  getAllBuses(): Observable<Bus[]> {
    return this.http.get<Bus[]>(this.apiUrl, {
      headers: this.getAuthHeaders()
    });
  }

  getBusById(busId: number): Observable<Bus> {
    return this.http.get<Bus>(`${this.apiUrl}/${busId}`, {
      headers: this.getAuthHeaders()
    });
  }

  updateBus(busId: number, requestObject: Bus): Observable<Bus> {
    return this.http.put<Bus>(`${this.apiUrl}/${busId}`, requestObject, {
      headers: this.getAuthHeaders()
    });
  }

  deleteBus(busId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${busId}`, {
      headers: this.getAuthHeaders()
    });
  }
}
