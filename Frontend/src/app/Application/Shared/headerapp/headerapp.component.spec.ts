import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderappComponent } from './headerapp.component';

describe('HeaderappComponent', () => {
  let component: HeaderappComponent;
  let fixture: ComponentFixture<HeaderappComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeaderappComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderappComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
