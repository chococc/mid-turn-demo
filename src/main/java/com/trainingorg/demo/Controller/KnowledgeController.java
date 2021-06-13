package com.trainingorg.demo.Controller;

import com.trainingorg.demo.Service.KnowledgeService;
import com.trainingorg.demo.bean.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    protected HttpRequest httpRequest=new HttpRequest();
    protected KnowledgeService knowledgeService=new KnowledgeService();

    @RequestMapping("")
    public HttpRequest run(@RequestParam("id") String id){
        return knowledgeService.run(id);
    }
}
