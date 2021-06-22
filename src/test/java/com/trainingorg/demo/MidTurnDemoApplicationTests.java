package com.trainingorg.demo;

import com.trainingorg.demo.Service.Impl.ClassManagerServiceImpl;
import com.trainingorg.demo.Service.Impl.KnowledgeServiceImpl;
import com.trainingorg.demo.Service.Impl.LoginServiceImpl;
import com.trainingorg.demo.Service.LoginService;
import com.trainingorg.demo.Util.MysqlConnector;
import com.trainingorg.demo.Util.Token;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MidTurnDemoApplicationTests {
    @Autowired
    private LoginService loginService;
    @Test
    public void DataServerConnectionTest() {
        System.out.println(new MysqlConnector().Connection_test().getMessage());
    }

    @Test

    void AccessCodeTest() {
        System.out.println(loginService.login("test","pass").getRequestMessage());
    }

    @Test
    void TokenGeneratorTest(){
        System.out.println(new Token().TokenGenerator_test("1","pass").getMessage());
    }

    @Test
    void GetTokenTest() {
        System.out.println(new Token().getToken_test("1","pass"));
    }

    @Test
    void DeleteTokenTest(){
        System.out.println(new Token().TokenDeleter_test("").getMessage());
    }

    @Test
    void GetTokenTest4Cookie(){System.out.println(new Token().getToken_Cookie_Test());}

    @Test
    void selectAllClassTest(){System.out.println(new ClassManagerServiceImpl().selectAll().getData());}

    @Test
    void KnowledgeServiceTest1(){new KnowledgeServiceImpl().run("test1");}
}
