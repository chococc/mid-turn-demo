package com.trainingorg.midturndemo.Util;

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
     *
     * param sql
     * param args
     */
    public void update(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = mysqlConnector.getConnection();
            preparedStatement = connection
                    .prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mysqlConnector.release(preparedStatement, connection);
        }
    }

    /**
     * Select database(Traditional)
     * param sql
     * return
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
     * param clazz
     * param sql
     * param args
     * return
     */
    public <T> T get(Class<T> clazz, String sql, Object... args) {
        T entity = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        try {
            connection = mysqlConnector.getConnection();
            preparedStatement = connection
                    .prepareStatement(sql);
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mysqlConnector.release(result, preparedStatement, connection);
        }
        return entity;
    }

    /**
     * select database(not only one result(include one result))
     *
     * param clazz
     * param sql
     * param args
     * return
     */
    public <T> List<T> getForList(Class<T> clazz, String sql, Object... args) {
        List<T> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            connection = mysqlConnector.getConnection();
            preparedStatement = connection
                    .prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            result = preparedStatement.executeQuery();

            List<Map<String, Object>> values = handleResultSetToMapList(result);
            list = transferMapListToBeanList(clazz, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mysqlConnector.release(result, preparedStatement, connection);
        }
        return list;
    }

    /**
     * Get Object into List.
     * param clazz
     * param values
     * return
     * throws InstantiationException
     * throws IllegalAccessException
     * throws InvocationTargetException
     */
    public <T> List<T> transferMapListToBeanList(Class<T> clazz,
                                                 List<Map<String, Object>> values) throws InstantiationException,
            IllegalAccessException, InvocationTargetException {

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
     * return
     * throws SQLException
     */
    public List<Map<String, Object>> handleResultSetToMapList(
            ResultSet resultSet) throws SQLException {
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
     * return
     * throws SQLException
     */
    private List<String> getColumnLabels(ResultSet resultSet)
            throws SQLException {
        List<String> labels = new ArrayList<>();

        ResultSetMetaData resume = resultSet.getMetaData();
        for (int i = 0; i < resume.getColumnCount(); i++) {
            labels.add(resume.getColumnLabel(i + 1));
        }

        return labels;
    }

    /**
     * 通用查询方法，返回一个值（可能是统计值）
     *
     * param sql
     * param args
     * return
     */
    @SuppressWarnings("unchecked")
    public <E> E getForValue(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = mysqlConnector.getConnection();
            preparedStatement = connection
                    .prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return (E) resultSet.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mysqlConnector.release(resultSet, preparedStatement, connection);
        }
        return null;
    }
}