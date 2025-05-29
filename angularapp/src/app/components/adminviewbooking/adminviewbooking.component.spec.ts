import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminviewbookingComponent } from './adminviewbooking.component';

describe('AdminviewbookingComponent', () => {
  let component: AdminviewbookingComponent;
  let fixture: ComponentFixture<AdminviewbookingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminviewbookingComponent]
    });
    fixture = TestBed.createComponent(AdminviewbookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
