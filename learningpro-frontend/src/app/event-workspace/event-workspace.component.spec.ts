import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventWorkspaceComponent } from './event-workspace.component';

describe('EventWorkspaceComponent', () => {
  let component: EventWorkspaceComponent;
  let fixture: ComponentFixture<EventWorkspaceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventWorkspaceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventWorkspaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
