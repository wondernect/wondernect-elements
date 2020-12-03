package com.wondernect.elements.jdbc.client.util;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.jdbc.client.response.JDBCResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class JDBCClient {

    private static final Logger logger = LoggerFactory.getLogger(JDBCClient.class);

    //初始化数据库
    public JDBCResult initDatabase(String driver, String url, String username, String password, String databaseName) {
        Connection con = null;        //连接
        PreparedStatement pstmt = null;    //使用预编译语句
        boolean result;
        String message;
        try {
            Class.forName(driver);//执行驱动
            con = DriverManager.getConnection(url, username, password);//获取连接
            String createDatabaseSql = "create database if not exists " + databaseName + " default character set utf8 collate utf8_general_ci;";
            //初始化数据库
            pstmt = con.prepareStatement(createDatabaseSql);
            pstmt.execute();
            result = true;
            message = "初始化数据库成功";
        } catch (ClassNotFoundException e) {
            result = false;
            message = "初始化数据库时驱动加载异常，原因是" + e.getMessage();
        } catch (SQLException e) {
            logger.error("初始化数据库发生异常", e);
            result = false;
            message = "初始化数据库发生异常，原因是" + e.getMessage();
        } finally {
            try {
                if (con != null) con.close();  //必须要关
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                logger.error("初始化数据库时关闭连接发生异常", e);
            }
        }
        return new JDBCResult(result, message);
    }

    //赋权限
    public JDBCResult giveRights(int type, String driver, String url, String username, String password, String databaseName, String userName, String passWord) {
        Connection con = null;        //连接
        PreparedStatement pstmt = null;    //使用预编译语句
        PreparedStatement pstmtOne = null;    //使用预编译语句
        boolean result;
        String message;
        String createUserRightsSql;
        try {
            Class.forName(driver);//执行驱动
            con = DriverManager.getConnection(url, username, password);//获取连接
            if (type == 1) {
                //只读权限
                createUserRightsSql = "grant select on " + databaseName + ".* to " + userName + "@'%' identified by " + "'" + passWord + "';";
            } else if (type == 2) {
                //所有权限
                createUserRightsSql = "grant all privileges on " + databaseName + ".* to " + userName + "@'%' identified by " + "'" + passWord + "';";
            } else {
                throw new BusinessException("权限类型有误");
            }
            String flush = "flush privileges;";
            //赋权限
            pstmt = con.prepareStatement(createUserRightsSql);
            pstmt.execute();//赋权限
            pstmtOne = con.prepareStatement(flush);
            pstmtOne.execute();//刷新
            result = true;
            message = "权限赋予成功";

        } catch (ClassNotFoundException e) {
            logger.error("权限赋予时数据库驱动加载异常", e);
            result = false;
            message = "权限赋予时数据库驱动加载异常，原因是" + e.getMessage();
        } catch (SQLException e) {
            logger.error("权限赋予时发生异常", e);
            result = false;
            message = "权限赋予时发生异常，原因是" + e.getMessage();
        } finally {
            try {
                if (con != null) con.close();  //必须要关
                if (pstmt != null) pstmt.close();
                if (pstmtOne != null) pstmtOne.close();
            } catch (SQLException e) {
                logger.error("权限赋予时关闭连接发生异常", e);
            }
        }
        return new JDBCResult(result, message);
    }

    //收回权限
    public JDBCResult revokeRights(String driver, String url, String username, String password, String databaseName, String userName) {
        Connection con = null;        //连接
        PreparedStatement pstmt = null;    //使用预编译语句
        PreparedStatement pstmtOne = null;    //使用预编译语句
        boolean result;
        String message;
        try {
            Class.forName(driver);//执行驱动
            con = DriverManager.getConnection(url, username, password);//获取连接
            //收回权限
            //收回该用户对所有数据库的操作权限
//            String revokeUserRightsSql = "revoke all privileges,grant option from " + "'" + userName + "'" + "@'%';";
            //收回该用户对某一个数据的操作权限
            String revokeUserRightsSql = "revoke all privileges on " + databaseName + ".* from " + "'" + userName + "'" + "@'%';";
            String flush = "flush privileges;";
            pstmt = con.prepareStatement(revokeUserRightsSql);
            pstmt.execute();
            pstmtOne = con.prepareStatement(flush);
            pstmtOne.execute();//刷新
            result = true;
            message = "收回权限成功";

        } catch (ClassNotFoundException e) {
            logger.error("收回权限时数据库驱动加载异常", e);
            result = false;
            message = "收回权限时数据库驱动加载异常，原因是" + e.getMessage();
        } catch (SQLException e) {
            logger.error("收回权限时发生异常", e);
            result = false;
            message = "收回权限时发生异常，原因是" + e.getMessage();
        } finally {
            try {
                if (con != null) con.close();  //必须要关
                if (pstmt != null) pstmt.close();
                if (pstmtOne != null) pstmtOne.close();
            } catch (SQLException e) {
                logger.error("收回权限时关闭连接发生异常", e);
            }
        }
        return new JDBCResult(result, message);
    }

    //测试连接
    public JDBCResult testConnect(String driver, String url, String userName, String passWord) {
        Connection con = null;        //连接
        PreparedStatement pstmt = null;    //使用预编译语句
        boolean result;
        String message;
        try {
            Class.forName(driver);//执行驱动
            con = DriverManager.getConnection(url, userName, passWord);//获取连接
            String testSql = "SELECT 1 from dual;";
            //测试链接
            pstmt = con.prepareStatement(testSql);
            pstmt.execute();//测试链接
            result = true;
            message = "测试连接成功";

        } catch (ClassNotFoundException e) {
            logger.error("测试连接时数据库驱动加载异常", e);
            result = false;
            message = "测试连接时数据库驱动加载异常，原因是" + e.getMessage();
        } catch (SQLException e) {
            logger.error("测试连接时发生异常", e);
            result = false;
            message = "测试连接时发生异常，原因是" + e.getMessage();
        } finally {
            try {
                if (con != null) con.close();  //必须要关
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                logger.error("测试连接时关闭连接发生异常", e);
            }
        }
        return new JDBCResult(result, message);
    }
}
