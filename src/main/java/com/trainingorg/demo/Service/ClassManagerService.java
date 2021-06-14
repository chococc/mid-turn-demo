package com.trainingorg.demo.Service;

import com.alibaba.fastjson.JSON;
import com.trainingorg.demo.Util.NoToken;
import com.trainingorg.demo.Util.Token;
import com.trainingorg.demo.bean.HttpRequest;
import com.trainingorg.demo.dao.ClassManagerDao;
import org.springframework.stereotype.Service;

@Service
public class ClassManagerService {

    ClassManagerDao classManagerDao=new ClassManagerDao();

    public HttpRequest add(String teacherId, String courseID, int startWeek, int stopWeek){
        HttpRequest httpRequest=new HttpRequest();
        try{
            new Token().IdentityCheck("orgManager");
            classManagerDao.AddClass(teacherId,courseID,startWeek,stopWeek);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("班级创建成功");
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非管理员账号.");
            return httpRequest;
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(401);
            httpRequest.setRequestMessage("信息表单存在错误.");
        }
        return httpRequest;
    }

    public HttpRequest delete(String classID){
        HttpRequest httpRequest=new HttpRequest();
        try{
            new Token().IdentityCheck("orgManager");
            classManagerDao.deleteClass(classID);

            httpRequest.setCode(200);
            httpRequest.setRequestMessage("班级信息删除成功");
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非管理员账号.");
            return httpRequest;
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(402);
            httpRequest.setRequestMessage("课程信息删除失败");
        }
        return httpRequest;
    }

    public HttpRequest update(String teacherId, String courseID, int startTime, int stopTime){
        HttpRequest httpRequest=new HttpRequest();
        try{
            new Token().IdentityCheck("orgManager");
            classManagerDao.updateClass(teacherId,courseID,startTime,stopTime);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("班级信息修改成功");
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非管理员账号.");
            return httpRequest;
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(403);
            httpRequest.setRequestMessage("课程信息修改失败");
        }
        return httpRequest;
    }

    public HttpRequest setTime(int classId,String time1,String time2,String time3){
        HttpRequest httpRequest=new HttpRequest();
        try{
            classManagerDao.setTime(classId,time1,time2,time3);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("班级信息修改成功!");
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了错误的账号.");
            return httpRequest;
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(403);
            httpRequest.setRequestMessage("课程信息修改失败");
        }
        return httpRequest;
    }

    public HttpRequest flashState(){
        HttpRequest httpRequest=new HttpRequest();
        try{
            classManagerDao.flashStatus();
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("班级状态已更新");
        }catch(Exception e){
            e.printStackTrace();
            httpRequest.setCode(404);
            httpRequest.setRequestMessage("班级状态更新失败");
        }
        return httpRequest;
    }

    public HttpRequest checkHere(String studentID,int classID){
        HttpRequest httpRequest=new HttpRequest();
        try{
            classManagerDao.checkHere(studentID,classID);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("签到成功");
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("签到信息错误,请查看后台信息.");
            return httpRequest;
        } catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(405);
            httpRequest.setRequestMessage("签到失败,请联系管理员.");
        }
        return httpRequest;
    }

    public HttpRequest selectAll(){
        HttpRequest httpRequest=new HttpRequest();
        try{
            new Token().IdentityCheck("orgManager");
            httpRequest.setData(JSON.toJSON(classManagerDao.selectAll()));
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("数据拉取成功");
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非管理员账号.");
            return httpRequest;
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(406);
            httpRequest.setRequestMessage("数据拉取失败");
        }
        return httpRequest;
    }
}