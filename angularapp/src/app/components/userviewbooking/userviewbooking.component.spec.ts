import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserviewbookingComponent } from './userviewbooking.component';

describe('UserviewbookingComponent', () => {
  let component: UserviewbookingComponent;
  let fixture: ComponentFixture<UserviewbookingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserviewbookingComponent]
    });
    fixture = TestBed.createComponent(UserviewbookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
