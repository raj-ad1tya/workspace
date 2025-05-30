import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Booking } from '../models/booking.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BusService {

  private apiUrl = 'http://localhost:8080/api/bookings';

  constructor(private http: HttpClient) { }

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token') || '';
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }


  bookBus(booking: Booking): Observable<Booking> {

    const userId = localStorage.getItem('userId');
    if (!userId) {
      throw new Error('User ID not found in localStorage');
    }

    const body = {
      bookingDate: booking.bookingDate,
      status: booking.status,
      seatNumbers: booking.seatNumbers,
      numberOfSeats: booking.numberOfSeats,
      user: { userId: +userId }, // cast to number
      bus: { busId: booking.bus.busId }
    };

    return this.http.post<Booking>(this.apiUrl, body, {
      headers: this.getAuthHeaders()
    });
  }

  getUserBookings(userId: any): Observable<Booking[]> {
    return this.http.get<Booking[]>(`${this.apiUrl}/user/${userId}`, {
      headers: this.getAuthHeaders()
    });
  }

  updateBooking(bookingId: number, requestObject: Booking): Observable<Booking> {
    return this.http.put<Booking>(`${this.apiUrl}/${bookingId}`, requestObject, {
      headers: this.getAuthHeaders()
    });
  }

  getAllBookings(): Observable<Booking[]> {
    return this.http.get<Booking[]>(this.apiUrl, {
      headers: this.getAuthHeaders()
    });
  }

  getBookingById(bookingId: number): Observable<Booking> {
    return this.http.get<Booking>(`${this.apiUrl}/${bookingId}`, {
      headers: this.getAuthHeaders()
    });
  }

  cancelBooking(bookingId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${bookingId}`, {
      headers: this.getAuthHeaders()
    });
  }
}
