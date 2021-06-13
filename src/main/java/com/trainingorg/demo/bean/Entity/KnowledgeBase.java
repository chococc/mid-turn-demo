package com.trainingorg.demo.bean.Entity;

import lombok.Data;

@Data
public class KnowledgeBase {

    protected int id;
    protected int knowledgeId;
    protected String knowledgeName;
    protected int step;
    protected int mathF;
    protected String dataBase;
    protected String dataType;
    protected String sql;

}
