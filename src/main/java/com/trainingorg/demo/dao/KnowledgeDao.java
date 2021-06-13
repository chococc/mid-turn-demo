package com.trainingorg.demo.dao;

import com.trainingorg.demo.Util.MysqlActuator;
import com.trainingorg.demo.bean.Entity.KnowledgeBase;

import java.util.List;

public class KnowledgeDao {

    protected MysqlActuator mysqlActuator=new MysqlActuator();

    public List<KnowledgeBase> getKnowledge(String knowledgeName) throws Exception{
        return mysqlActuator.getForList(KnowledgeBase.class,"SELECT * FROM knowledgeBase where knowledgeName='"+knowledgeName+"' order by step");
    }

    public List<KnowledgeBase> selectAll() throws Exception {
        return mysqlActuator.getForList(KnowledgeBase.class,"SELECT * FROM knowledgeBase Group By knowledgeName order by step");
    }
}
