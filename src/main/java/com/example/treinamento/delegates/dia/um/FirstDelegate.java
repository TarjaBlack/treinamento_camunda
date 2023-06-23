package com.example.treinamento.delegates.dia.um;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("FirstDelegate")
public class FirstDelegate implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("Meu primeiro delegate java");
        Thread.sleep(5000);
        delegateExecution.setVariable("teste","OK");
    }
}
