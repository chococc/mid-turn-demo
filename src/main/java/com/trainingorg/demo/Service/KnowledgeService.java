package com.trainingorg.demo.Service;

import com.trainingorg.demo.bean.HttpRequest;

public interface KnowledgeService {
    HttpRequest run(String knowledgeName);
    HttpRequest selectAll();
}
