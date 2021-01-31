package com.pientaa;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;

@ProcessApplication("BookingService")
public class BookingService extends ServletProcessApplication {
}
