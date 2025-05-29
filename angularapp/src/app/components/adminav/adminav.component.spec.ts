import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminavComponent } from './adminav.component';

describe('AdminavComponent', () => {
  let component: AdminavComponent;
  let fixture: ComponentFixture<AdminavComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminavComponent]
    });
    fixture = TestBed.createComponent(AdminavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
