package com.trainingorg.midturndemo.Util;

import com.trainingorg.midturndemo.Util.RequestMessage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Mysql Database Connector.
 * version 1.0
 * date 2021.4.20
 * return java.sql.connection
 * System Exception Code 01
 */
public class MysqlConnector {
    Connection con;
    protected RequestMessage tryConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://39.103.196.184:3306/TrainingOrg","root","Kinoko@7");
            return new RequestMessage().DataSource_Success();
        }catch(Exception e){
            return new RequestMessage().DataSource_Error(e);
        }
    }

    public void release(ResultSet resultSet,Statement statement,Connection connection){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    public void release(Statement statement,Connection connection){
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    public Connection getConnection(){
        tryConnection();
        return con;
    }
    //Mysql Database Connector Test Interface.
    public RequestMessage Connection_test(){
        return tryConnection();
    }
}