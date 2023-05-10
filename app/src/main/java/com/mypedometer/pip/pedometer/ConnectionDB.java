package com.mypedometer.pip.pedometer;

import android.content.Context;

import com.yugabyte.ysql.YBClusterAwareDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {

    //database connection
    private static final String host = "europe-central2.864cfdd1-12ac-4ced-b28d-ae03cbeb2101.gcp.ybdb.io";
    private static final String port = "5433";
    private static final String database = "postgres";
    private static final String user = "admin";
    private static final String password = "kS5lkG416PfR1I9K-z0AfjzbreHjZn";
    private static final String url = "jdbc:yugabytedb:/" + host + ":" + port + "/" + database + "?user=" + user + "&password=" + password;

    private static final String urlShield = "./ysqlsh \"host=europe-central2.864cfdd1-12ac-4ced-b28d-ae03cbeb2101.gcp.ybdb.io \\\n" +
            "user=Users\\" +
            "dbname=postgres\\" +
            "sslmode=verify-full\\" +
            "sslrootcert=./root.crt";




    //database communication
    //private Context m_Context;
    private Connection m_Connection;
    private Statement m_Statement;
    private ResultSet m_ResultSet;

    public ConnectionDB() {
        //this.m_Context = context;
    }

    public Connection getConnection() {
        try {
            System.out.println("Loading driver...");
            Class.forName("com.yugabyte.ysql.YBClusterAwareDataSource");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Connecting to database...");
            m_Connection = DriverManager.getConnection(url);//"jdbc:yugabytedb://"+host+":"+port+"/"+database+"?user="+user+"&password="+password);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to database: " + e.getMessage());
        }
        return m_Connection;
    }


    public Statement getStatement() {
        try {
            m_Statement = m_Connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m_Statement;
    }

    public ResultSet getResultSet(String sql) {
        try {
            m_ResultSet = m_Statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m_ResultSet;
    }

    public void close() {
        try {
            if (m_ResultSet != null) {
                m_ResultSet.close();
            }
            if (m_Statement != null) {
                m_Statement.close();
            }
            if (m_Connection != null) {
                m_Connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        m_ResultSet = null;
    }
}
