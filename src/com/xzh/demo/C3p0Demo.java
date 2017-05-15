package com.xzh.demo;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhenghangxia on 17-5-15.
 */
public class C3p0Demo {

    private static Connection conn;
    private static ComboPooledDataSource dataSource;

    private static void getConnection() {
        try {
            // 获取配置文件数据原
            dataSource = new ComboPooledDataSource();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        getConnection();

        queryData();

        insertData();

        updateData();

        deleteData();

    }

    /**
     *  删除数据
     */
    private static void deleteData() {

        try {

            // 获取链接
            conn = dataSource.getConnection();

            String deleteSql = "delete from users where id=?";

            PreparedStatement ps = conn.prepareStatement(deleteSql);
            ps.setInt(1,2);

            int row = ps.executeUpdate();

            System.out.println("delete success " + row);

            conn.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *  更新数据
     */
    private static void updateData() {

        try {

            // 获取链接
            conn = dataSource.getConnection();

            String updateSql = "update users set name=?,password=? where id=?";

            PreparedStatement ps = conn.prepareStatement(updateSql);
            ps.setString(1,"xzh1");
            ps.setString(2,"1234567");
            ps.setInt(3,2);

            int row = ps.executeUpdate();

            System.out.println("update success " + row);

            conn.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 添加数据
     */
    private static void insertData() {

        try {

            // 获取链接
            conn = dataSource.getConnection();

            String insertSql = "insert into users(name,password) values (?,?)";

            PreparedStatement ps = conn.prepareStatement(insertSql);
            ps.setString(1,"xzh");
            ps.setString(2,"123456");
            int row = ps.executeUpdate();
            System.out.println("insert success " + row);

            conn.close();
            ps.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 从数据库中读取数据
     */
    private static void queryData() {
        try{

            // 获取链接
            conn = dataSource.getConnection();
            // sql 语句
            String querySql = "select * from users";

            // 读取数据
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }

            conn.close();
            ps.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
