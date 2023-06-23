package com.example.treinamento.delegates.dia;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("finalizarRestricaoDelegate")
@Slf4j
public class FinalizarRestricao implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String key = delegateExecution.getBusinessKey();
        String messageName = "RETRICAO_FINALIZADA";

        Boolean ultimaEtapa = (Boolean)delegateExecution.getVariable("ultimaEtapa");

        if (ultimaEtapa){
            log.info("Ultima etapa finalizada, enviando mensagem");

            processEngine.getRuntimeService()
                    .createMessageCorrelation(messageName)
                    .processInstanceBusinessKey(key)
                    .correlateWithResult();
        }else {
            log.info("Ultima etapa não finalizada, não há necessidade do envio da mensagem");
        }

    }
}
