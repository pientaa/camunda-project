package com.pientaa;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.Map;

public class BookingRequest implements JavaDelegate {
    public void execute(DelegateExecution execution) {
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        Map<String, Object> processVariables = new HashMap<>();
        processVariables.put("parentBussinesKey", execution.getProcessInstanceId());
        processVariables.put("apartmentName", execution.getVariable("apartmentName"));
        processVariables.put("numberOfRooms", execution.getVariable("numberOfRooms"));
        processVariables.put("apartmentSize", execution.getVariable("apartmentSize"));
        processVariables.put("apartmentDescription", execution.getVariable("apartmentDescription"));
        processVariables.put("availableFrom", execution.getVariable("availableFrom"));
        processVariables.put("reservationDuration", execution.getVariable("reservationDuration"));
        processVariables.put("transactionNumber", execution.getVariable("transactionNumber"));
        processVariables.put("paymentStatus", execution.getVariable("paymentStatus"));
        processVariables.put("opinion", execution.getVariable("opinion"));
        runtimeService.startProcessInstanceByMessage("BookingRequest", processVariables);
    }
}