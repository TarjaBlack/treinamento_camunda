package com.example.treinamento.controller;

import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstanceQuery;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.treinamento.util.DateUtil.millecondsBeautiful;
import static com.example.treinamento.util.StringUtil.safeToString;

@RestController
@AllArgsConstructor
public class Controller {

    private RuntimeService runtimeService;
    private TaskService taskService;
    private HistoryService historyService;

    @PostMapping("/mensagem")
    public ResponseEntity<String> mensagemCamunda(@RequestParam(value = "businessKey") String businessKey,
                                                                   @RequestParam(value = "msg")String msg){
        MessageCorrelationResult restricao = runtimeService.createMessageCorrelation(msg)
                .processInstanceBusinessKey(businessKey).correlateWithResult();

        return ResponseEntity.ok("mensagem enviada");
    }

    @PostMapping("/criar")
    public ResponseEntity<String> iniciarProcesso(@RequestParam(value = "businessKey") String businessKey,@RequestParam(value = "flowId") String flowId){

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(flowId,businessKey);

        return ResponseEntity.ok("Processo iniciado com sucesso: " + processInstance.getBusinessKey());
    }

    @GetMapping("/tarefas")
    public ResponseEntity<List<HashMap>> recuperarListaDeAtividades(@RequestParam(value = "businessKey") String businessKey){
        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceBusinessKey(businessKey)
                .list();

        List<HashMap> listaCompleta = new ArrayList<>();

        tasks.forEach( e-> {
            HashMap<String, String> map = new HashMap<>();
            map.put("id",e.getId());
            map.put("nome",e.getName());
            listaCompleta.add(map);
        });

        return ResponseEntity.ok(listaCompleta);
    }

    @PutMapping("/completar")
    public ResponseEntity<String> completarTarefa(@RequestParam(value = "taskId") String taskId){
        taskService.complete(taskId);
        return ResponseEntity.ok("Tarefa finalizada com sucesso");
    }


    @GetMapping("/historico")
    public ResponseEntity<Object> recuperarHistorico(@RequestParam(value = "businessKey") String businessKey){

        HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .orderByHistoricTaskInstanceEndTime().asc();

        List<HistoricTaskInstance> historicTaskInstances = query.list();


        List<HashMap> listaCompleta = new ArrayList<>();

        historicTaskInstances.forEach( e-> {
            HashMap<String, String> map = new HashMap<>();
            map.put("id",e.getId());
            map.put("nome",e.getName());
            map.put("dataInicio",safeToString(e.getStartTime()));
            map.put("dataFinal",safeToString(e.getEndTime()));
            map.put("duracao", millecondsBeautiful(e.getDurationInMillis()));
            listaCompleta.add(map);
        });


        return ResponseEntity.ok(listaCompleta);
    }

}
