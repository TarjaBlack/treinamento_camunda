package com.example.treinamento.delegates.dia.dois;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("implementacaoDelegate")
@Slf4j
public class ImplementacaoDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String tipo = (String) delegateExecution.getVariable("tipo");
        String nome = (String) delegateExecution.getVariable("nomeSoftware");

        log.info("implementando o código do {} do projeto {}", tipo,nome );
    }
}
