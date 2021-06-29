package com.trainingorg.demo.Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Mysql-SQL语句执行器
 * Date 2021/5/6
 * param MysqlConnector.con(java.sql.MysqlConnector)
 */
public class MysqlActuator {

    MysqlConnector mysqlConnector=new MysqlConnector();

    /**
     * update database(include:UPDATE INSERT DELETE ...)
     * param sql,args
     */
    public void update(String sql, Object... args) throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;
        connection = mysqlConnector.getConnection();
        preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }

        preparedStatement.executeUpdate();
        mysqlConnector.release(preparedStatement, connection);
    }

    /**
     * Select database(Traditional)
     * param sql
     * return ResultSet
     */
    public ResultSet getResultSet_Select(String sql){

        Connection connection=mysqlConnector.getConnection();
        ResultSet rs;
        Statement stmt;

        try{
            stmt=connection.createStatement();
        }catch (Exception e){
            return null;
        }
        try {
            rs = stmt.executeQuery(sql);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return rs;
    }

    /**
     * select database(only one result)
     *
     * param Object.clazz,sql,args
     * return Object.class
     */
    public <T> T get(Class<T> clazz, String sql, Object... args) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {

        T entity = null;
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet result;
        connection = mysqlConnector.getConnection();

        preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            result = preparedStatement.executeQuery();
            Map<String, Object> map = new HashMap<>();
            ResultSetMetaData resumed = result.getMetaData();

            if (result.next()) {
                for (int i = 0; i < resumed.getColumnCount(); i++) {
                    String columnLabel = resumed.getColumnLabel(i + 1);
                    Object value = result.getObject(i + 1);
                    map.put(columnLabel, value);
                }
            }
            if (map.size() > 0) {
                entity = clazz.newInstance();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String filedName = entry.getKey();
                    Object filedObject = entry.getValue();
                    BeanUtils.setProperty(entity, filedName, filedObject);
                }
            }
        return entity;
    }

    /**
     * select database(not only one result(include one result))
     * param Object.clazz,sql,args
     * return Object
     */
    public <T> List<T> getForList(Class<T> clazz, String sql, Object... args) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {

        List<T> list;
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet result;
        connection = mysqlConnector.getConnection();
        preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }

        result = preparedStatement.executeQuery();

        List<Map<String, Object>> values = handleResultSetToMapList(result);
        list = transferMapListToBeanList(clazz, values);
        mysqlConnector.release(result, preparedStatement, connection);
        return list;
    }

    /**
     * Get Object into List.
     * param clazz,values
     * return List<Object>
     * throws InstantiationException,IllegalAccessException,InvocationTargetException
     */
    public <T> List<T> transferMapListToBeanList(Class<T> clazz, List<Map<String, Object>> values) throws InstantiationException, IllegalAccessException, InvocationTargetException {

        List<T> result = new ArrayList<>();

        T bean;

        if (values.size() > 0) {
            for (Map<String, Object> m : values) {
                bean = clazz.newInstance();
                for (Map.Entry<String, Object> entry : m.entrySet()) {
                    String propertyName = entry.getKey();
                    Object value = entry.getValue();

                    BeanUtils.setProperty(bean, propertyName, value);
                }
                // 13. 把 Object 对象放入到 list 中.
                result.add(bean);
            }
        }

        return result;
    }

    /**
     * turn results to list.
     * param resultSet
     * return values
     * throws SQLException
     */
    public List<Map<String, Object>> handleResultSetToMapList(ResultSet resultSet) throws SQLException {

        List<Map<String, Object>> values = new ArrayList<>();

        List<String> columnLabels = getColumnLabels(resultSet);
        Map<String, Object> map;

        while (resultSet.next()) {
            map = new HashMap<>();

            for (String columnLabel : columnLabels) {
                Object value = resultSet.getObject(columnLabel);
                map.put(columnLabel, value);
            }
            values.add(map);
        }
        return values;
    }

    /**
     *
     * param resultSet
     * return labels
     * throws SQLException
     */
    private List<String> getColumnLabels(ResultSet resultSet) throws SQLException {

        List<String> labels = new ArrayList<>();

        ResultSetMetaData resume = resultSet.getMetaData();
        for (int i = 0; i < resume.getColumnCount(); i++) {
            labels.add(resume.getColumnLabel(i + 1));
        }
        return labels;
    }

    /**
     * Traditional Result，return one value（count is available）
     *
     * param sql,args
     * return value
     */
    @SuppressWarnings("unchecked")
    public <E>E getForValue(String sql, Object... args) throws SQLException,NullPointerException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        E result;
        connection = mysqlConnector.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        for (int i = 0; i < args.length; i++) {
            assert preparedStatement != null;
            preparedStatement.setObject(i + 1, args[i]);
        }
        assert preparedStatement != null;
        resultSet = preparedStatement.executeQuery();
        result=(E)resultSet.getObject(1);
        mysqlConnector.release(resultSet, preparedStatement, connection);
        return result;
    }

    /**
     * Traditional Result，return more than one values（count is available）
     *
     * param sql,args
     * return values
     */

    @SuppressWarnings("unchecked")
    public <E> List<E> getForValueList(String sql, Object... args) throws SQLException,NullPointerException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        List<E> list = new ArrayList<>();
        int j=1;
        connection = mysqlConnector.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        for (int i = 0; i < args.length; i++) {
            assert preparedStatement != null;
            preparedStatement.setObject(i + 1, args[i]);
        }
        assert preparedStatement != null;
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            list.add((E)resultSet.getObject(j));
            j++;
        }
        mysqlConnector.release(resultSet, preparedStatement, connection);
        return list;
    }
}