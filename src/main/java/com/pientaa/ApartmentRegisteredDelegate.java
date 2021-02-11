package com.pientaa;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

public class ApartmentRegisteredDelegate implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("com.pientaa.ApartmentRegisteredDelegate");

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("apartmentName", "ChangedName");
        LOGGER.info("Apartment name: '" + delegateExecution.getVariable("apartmentName") + "'...");
    }
}
