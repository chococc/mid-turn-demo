package com.trainingorg.demo.Service.Impl;

import com.trainingorg.demo.Service.KnowledgeService;
import com.trainingorg.demo.Util.MysqlActuator;
import com.trainingorg.demo.Util.SQLMath;
import com.trainingorg.demo.bean.Entity.KnowledgeBase;
import com.trainingorg.demo.bean.HttpRequest;
import com.trainingorg.demo.dao.KnowledgeDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class KnowledgeServiceImpl implements KnowledgeService {

    protected HttpRequest httpRequest = new HttpRequest();
    protected KnowledgeDao knowledgeDao = new KnowledgeDao();
    protected MysqlActuator mysqlActuator = new MysqlActuator();
    protected SQLMath sqlMath = new SQLMath();

    public HttpRequest run(String knowledgeName) {
        try {
            List<KnowledgeBase> knowledgeList = knowledgeDao.getKnowledge(knowledgeName);
            List<Float> number0 = new ArrayList<>();
            for (int i=0;i<knowledgeList.size();i++) {
                KnowledgeBase knowledgeBase=knowledgeList.get(i);
                if(i==knowledgeList.size()-1&&knowledgeBase.getSql()==null){
                    httpRequest.setData(sqlMath.calculator(knowledgeBase.getMathF(),number0));
                    break;
                }else{
                    httpRequest.setData(number0);
                }
                try {
                    List<Float> number = mysqlActuator.getForValue(knowledgeBase.getSql());
                    number0.add(sqlMath.calculator(knowledgeBase.getMathF(), number));
                } catch (Exception e) {
                    try {
                        List<Long> number = mysqlActuator.getForValue(knowledgeBase.getSql());
                        number0.add(sqlMath.calculateLong(knowledgeBase.getMathF(),number));
                    } catch (Exception a) {
                        try {
                            List<Integer> number = mysqlActuator.getForValue(knowledgeBase.getSql());
                            number0.add(sqlMath.calculateInteger(knowledgeBase.getMathF(), number));
                        }catch (Exception b){
                            httpRequest.setCode(702);
                            httpRequest.setRequestMessage("??????????????????????????????????????????");
                        }
                    } finally {
                        httpRequest.setCode(200);
                        httpRequest.setRequestMessage("????????????");
                    }
                }
            }
        } catch (Exception e) {
            httpRequest.setCode(701);
            httpRequest.setRequestMessage("????????????,?????????sql???????????????");
        }
        System.out.println(httpRequest.getData());
        return httpRequest;
    }

    public HttpRequest selectAll(){
        try{
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("????????????");
            httpRequest.setData(knowledgeDao.selectAll());
        }catch (Exception e){
            httpRequest.setCode(702);
            httpRequest.setRequestMessage(e.getMessage());
            e.printStackTrace();
        }
        return httpRequest;
    }
}
