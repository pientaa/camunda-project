package com.pientaa;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

public class BookApartmentDelegate implements JavaDelegate {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://postgres:5432/camunda-db";
    static final String USER = "postgres";
    static final String PASS = "postgres";
    private final static Logger LOGGER = Logger.getLogger("com.pientaa.BookApartmentDelegate");


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Connection conn = null;
        Statement stmt = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "select name, rooms, size, description, available_from, duration" +
                    " from apartment where name = '" + delegateExecution.getVariable("apartmentName") + "'";

            ResultSet rs = stmt.executeQuery(sql);
            rs.next();

//            TODO: Set variables

            LOGGER.info(rs.getString("rooms"));
            LOGGER.info(rs.getString("size"));
            LOGGER.info(rs.getString("description"));
            LOGGER.info(rs.getString("available_from"));
            LOGGER.info(rs.getString("duration"));

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
                //Do nothing
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}

