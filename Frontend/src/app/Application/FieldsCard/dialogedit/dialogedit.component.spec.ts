import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogeditComponent } from './dialogedit.component';

describe('DialogeditComponent', () => {
  let component: DialogeditComponent;
  let fixture: ComponentFixture<DialogeditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogeditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogeditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
