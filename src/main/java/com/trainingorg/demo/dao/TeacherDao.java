package com.trainingorg.demo.dao;

import com.trainingorg.demo.Util.MysqlActuator;
import com.trainingorg.demo.Util.SQLUtils;
import com.trainingorg.demo.Util.TimeStamp;
import com.trainingorg.demo.Util.Token;
import com.trainingorg.demo.bean.Entity.ClassEntity;
import com.trainingorg.demo.bean.Entity.StudentClassEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherDao {

    protected MysqlActuator mysqlActuator=new MysqlActuator();

    public List<ClassEntity> getClassListByInstance(String instance) throws Exception{
        Token token=new Token();
        String username=token.Token2Username();
        if(instance==null){
            instance=new TimeStamp().getInstance();
        }
        return mysqlActuator.getForList(ClassEntity.class,"SELECT * FROM classList where teacherID='"+username+"' AND (ClassTime1 like '+"+instance+"%'or ClassTime2 like '"+instance+"%' or ClassTime3 like '"+instance+"%') and status=1");
    }

    public List<ClassEntity> getClassListByTeacherID() throws Exception{
        Token token=new Token();
        String username=token.Token2Username();
        return mysqlActuator.getForList(ClassEntity.class,"SELECT * FROM classList where teacherID='"+username+"'");
    }

    public List<StudentClassEntity> getStudentList(String classID) throws Exception{
        return mysqlActuator.getForList(StudentClassEntity.class,"SELECT * FROM studentClass where classID='+"+classID+"'");
    }

    public void setGrade(int classID,String studentID,int grade) throws Exception{
        //mysqlActuator.update("UPDATE studentClass SET grade="+grade+" where classID='"+classID+"' and studentID='"+studentID+"'");
        Map<String,Object> map=new HashMap<>();
        map.put("studentGrade",grade);
        map.put("Key_classID",classID);
        map.put("Key_studentID",studentID);
        mysqlActuator.update(SQLUtils.getSql("StudentClass","update",map,false,""));
    }
}
