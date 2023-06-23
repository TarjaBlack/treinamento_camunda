package com.example.treinamento.delegates.dia.dois;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component("LevantamentoDeRequisitosDelegate")
public class LevantamentoDeRequisitosDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String nomeSoftware = (String)  delegateExecution.getVariable("nomeSoftware");

        log.info("Requisição do software {} foi aprovada",nomeSoftware);
        log.info("Iniciando análise de requisitos para o sofware {}",nomeSoftware);
    }
}
