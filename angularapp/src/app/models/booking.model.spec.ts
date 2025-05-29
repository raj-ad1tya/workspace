import { Booking } from './booking.model';

describe('Booking Model', () => {

  fit('frontend_Booking_model_should_create_an_instance', () => {
    // Create a sample Booking object
    const booking: Booking = {
      userId: 456,
      busId: 101,
      bookingDate: '2025-04-10',
      seatNumbers: [5, 6, 7],
      status: 'CONFIRMED'
    };

    expect(booking).toBeTruthy();
    expect(booking.userId).toBe(456);
    expect(booking.busId).toBe(101);
    
  });

});
