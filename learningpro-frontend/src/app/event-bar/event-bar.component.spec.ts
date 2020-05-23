import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventBarComponent } from './event-bar.component';

describe('EventBarComponent', () => {
  let component: EventBarComponent;
  let fixture: ComponentFixture<EventBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
