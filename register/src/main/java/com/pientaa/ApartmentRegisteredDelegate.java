package com.pientaa;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class ApartmentRegisteredDelegate implements JavaDelegate {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://postgres:5432/camunda-db";
    static final String USER = "postgres";
    static final String PASS = "postgres";

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Connection conn = null;
        Statement stmt = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "insert into apartment(name, rooms, size, description, available_from, duration)  " +
                    "values ('" + delegateExecution.getVariable("apartmentName") + "', " +
                    delegateExecution.getVariable("numberOfRooms") + ", " +
                    delegateExecution.getVariable("apartmentSize") + ", '" +
                    delegateExecution.getVariable("apartmentDescription") + "', '" +
                    format.format(delegateExecution.getVariable("availableFrom")) + "', " +
                    delegateExecution.getVariable("reservationDuration") + ")";

            stmt.executeUpdate(sql);

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
