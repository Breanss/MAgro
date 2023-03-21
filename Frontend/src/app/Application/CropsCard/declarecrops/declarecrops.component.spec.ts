import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeclarecropsComponent } from './declarecrops.component';

describe('DeclarecropsComponent', () => {
  let component: DeclarecropsComponent;
  let fixture: ComponentFixture<DeclarecropsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeclarecropsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeclarecropsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
