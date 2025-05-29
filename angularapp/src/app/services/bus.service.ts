import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BusService {

  private apiUrl = 'http://localhost:8080/api/bookings';

  constructor(private http: HttpClient) { }

  // Helper method to get Authorization header
  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token') || '';
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  // Book a bus
  bookBus(booking: Booking): Observable<Booking> {
    // Extract userId from localStorage (assuming you store it there)
    const userId = localStorage.getItem('userId');
    if (!userId) {
      throw new Error('User ID not found in localStorage');
    }

    // Build request body (adjust if backend expects different structure)
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

  // Get bookings by user
  getUserBookings(userId: any): Observable<Booking[]> {
    return this.http.get<Booking[]>(`${this.apiUrl}/user/${userId}`, {
      headers: this.getAuthHeaders()
    });
  }

  // Update booking
  updateBooking(bookingId: number, requestObject: Booking): Observable<Booking> {
    return this.http.put<Booking>(`${this.apiUrl}/${bookingId}`, requestObject, {
      headers: this.getAuthHeaders()
    });
  }

  // Get all bookings
  getAllBookings(): Observable<Booking[]> {
    return this.http.get<Booking[]>(this.apiUrl, {
      headers: this.getAuthHeaders()
    });
  }

  // Get booking by ID
  getBookingById(bookingId: number): Observable<Booking> {
    return this.http.get<Booking>(`${this.apiUrl}/${bookingId}`, {
      headers: this.getAuthHeaders()
    });
  }

  // Cancel booking
  cancelBooking(bookingId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${bookingId}`, {
      headers: this.getAuthHeaders()
    });
  }
}
