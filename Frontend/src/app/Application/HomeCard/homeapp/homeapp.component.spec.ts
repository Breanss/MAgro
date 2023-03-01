import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeappComponent } from './homeapp.component';

describe('HomeappComponent', () => {
  let component: HomeappComponent;
  let fixture: ComponentFixture<HomeappComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeappComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeappComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
