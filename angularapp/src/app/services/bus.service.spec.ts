import { TestBed } from '@angular/core/testing';

import { BusService } from './bus.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('BusService', () => {
  let service: BusService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],  // Import HttpClientTestingModule for HTTP requests
    });
    service = TestBed.inject(BusService);
  });

  fit('frontend_should_create_bus_service', () => {
    expect(service).toBeTruthy();
  });
});
