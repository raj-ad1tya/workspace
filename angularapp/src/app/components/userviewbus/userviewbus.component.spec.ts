import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserviewbusComponent } from './userviewbus.component';

describe('UserviewbusComponent', () => {
  let component: UserviewbusComponent;
  let fixture: ComponentFixture<UserviewbusComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserviewbusComponent]
    });
    fixture = TestBed.createComponent(UserviewbusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
