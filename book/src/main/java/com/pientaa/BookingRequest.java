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
        runtimeService.startProcessInstanceByMessage("BookingRequest", processVariables);
    }
}