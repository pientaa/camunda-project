package com.pientaa;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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

            Integer rooms = rs.getInt("rooms");
            Integer size = rs.getInt("size");
            String description = rs.getString("description");
            Date from = rs.getTimestamp("available_from");
            Integer duration = rs.getInt("duration");


            delegateExecution.setVariable("numberOfRooms", rooms);
            delegateExecution.setVariable("apartmentSize", size);
            delegateExecution.setVariable("apartmentDescription", description);
            delegateExecution.setVariable("availableFrom", from);
            delegateExecution.setVariable("reservationDuration", duration);
            delegateExecution.setVariable("isApartmentAvailable", true);

            LOGGER.info(String.valueOf(rooms));
            LOGGER.info(String.valueOf(size));
            LOGGER.info(description);
            LOGGER.info(String.valueOf(from));
            LOGGER.info(String.valueOf(duration));

        } catch (SQLException se) {
            delegateExecution.setVariable("isApartmentAvailable", false);
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

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql_2 = "delete from apartment where name = '" + delegateExecution.getVariable("apartmentName") + "'";

            stmt.execute(sql_2);

        } catch (Exception se) {
            se.printStackTrace();
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

