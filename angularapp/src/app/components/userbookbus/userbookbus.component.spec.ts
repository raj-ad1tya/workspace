import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserbookbusComponent } from './userbookbus.component';

describe('UserbookbusComponent', () => {
  let component: UserbookbusComponent;
  let fixture: ComponentFixture<UserbookbusComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserbookbusComponent]
    });
    fixture = TestBed.createComponent(UserbookbusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
