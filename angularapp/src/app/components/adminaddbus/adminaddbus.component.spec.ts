import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminaddbusComponent } from './adminaddbus.component';

describe('AdminaddbusComponent', () => {
  let component: AdminaddbusComponent;
  let fixture: ComponentFixture<AdminaddbusComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminaddbusComponent]
    });
    fixture = TestBed.createComponent(AdminaddbusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
