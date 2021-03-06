package com.trainingorg.demo.Controller;

import com.trainingorg.demo.Service.KnowledgeService;
import com.trainingorg.demo.bean.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Resource
    protected KnowledgeService knowledgeService;
    protected HttpRequest httpRequest=new HttpRequest();

    @RequestMapping("")
    public HttpRequest run(@RequestParam("id") String id){
        return knowledgeService.run(id);
    }

    @RequestMapping("/selectAll")
    public HttpRequest selectAll(){return knowledgeService.selectAll();}

}
