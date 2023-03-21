import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogdeleteComponent } from './dialogdelete.component';

describe('DialogdeleteComponent', () => {
  let component: DialogdeleteComponent;
  let fixture: ComponentFixture<DialogdeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogdeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogdeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
