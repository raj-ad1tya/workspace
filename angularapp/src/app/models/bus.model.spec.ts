import { Bus } from './bus.model';

describe('Bus Model', () => {

  fit('frontend_Bus_model_should_create_an_instance', () => {
    // Create a sample Bus object
    const bus: Bus = {
      busName: 'Express Line',
      source: 'City A',
      destination: 'City B',
      departureTime: '08:00 AM',
      arrivalTime: '12:00 PM',
      totalSeats: 50,
      availableSeats: 20,
      busType: 'AC Sleeper',
      fare: 500,
      photo: 'base64_encoded_image_string',
      userId: 123
    };

    expect(bus).toBeTruthy();
    expect(bus.busName).toBe('Express Line');
    expect(bus.source).toBe('City A');
    expect(bus.destination).toBe('City B');
  });

});
