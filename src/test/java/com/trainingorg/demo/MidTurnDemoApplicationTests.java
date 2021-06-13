package com.trainingorg.demo;

import com.trainingorg.demo.Service.ClassManagerService;
import com.trainingorg.demo.Service.KnowledgeService;
import com.trainingorg.demo.bean.Authenticator;
import com.trainingorg.demo.Util.MysqlConnector;
import com.trainingorg.demo.Util.Token;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
    void selectAllClassTest(){System.out.println(new ClassManagerService().selectAll().getRequestData());}

    @Test
    void KnowledgeServiceTest1(){new KnowledgeService().run("test1");}
}
