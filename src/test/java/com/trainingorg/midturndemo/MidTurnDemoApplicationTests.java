package com.trainingorg.midturndemo;

import com.trainingorg.midturndemo.Util.MysqlActuator;
import com.trainingorg.midturndemo.bean.Authenticator;
import com.trainingorg.midturndemo.Util.MysqlConnector;
import com.trainingorg.midturndemo.Util.Token;
import com.trainingorg.midturndemo.bean.Entity.ClassEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@SpringBootTest
class MidTurnDemoApplicationTests {

    @Test
    void DataServerConnectionTest() {
        System.out.println(new MysqlConnector().Connection_test().getMessage());
    }

    @Test
    void AccessCodeTest() {
        Authenticator authenticator=new Authenticator();
        System.out.println(authenticator.Access_Test("1","pass").getMessage());
    }

    @Test
    void TokenGeneratorTest(){
        System.out.println(new Token().TokenGenerator_test("1","pass").getMessage());
    }

    @Test
    void GetTokenTest(){
        System.out.println(new Token().getToken_test("1","pass"));
    }

    @Test
    void DeleteTokenTest(){
        System.out.println(new Token().TokenDeleter_test("").getMessage());
    }

    @Test
    void GetTokenTest4Cookie(){System.out.println(new Token().getToken_Cookie_Test());}

    @Test
    void selectAllClassTest() throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {System.out.println(new MysqlActuator().getForList(ClassEntity.class,"SELECT * FROM classList"));}
}
