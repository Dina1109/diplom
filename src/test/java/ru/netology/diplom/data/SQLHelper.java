package ru.netology.diplom.data;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;


public class SQLHelper {

    private SQLHelper() {
    }

    @SneakyThrows
    public static Connection getConnection() {
        String dbUrl = System.getProperty("db.url");
        String login = System.getProperty("login");
        String password = System.getProperty("password");
        final Connection connection = DriverManager.getConnection(dbUrl, login, password);
        return connection;
    }

    @SneakyThrows
    public static String getPaymentId() {
        String payment_Id = null;
        var idSQL = "SELECT payment_id FROM order_entity order by created DESC;";
        try (var conn = getConnection();
             var statusStmt = conn.prepareStatement(idSQL)) {
            try (var rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    payment_Id = rs.getString("payment_id");
                }
            }
        }
        return payment_Id;
    }

    @SneakyThrows
    public static String getStatusPayment(String paymentId) {
        String statusSQL = "SELECT status FROM payment_entity WHERE transaction_id =?; ";
        String status = null;
        try (var conn = getConnection();
             var statusStmt = conn.prepareStatement(statusSQL)) {
            statusStmt.setString(1, paymentId);
            try (var rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }

    @SneakyThrows
    public static String getStatusCredit(String paymentId) {
        String statusSQL = "SELECT status FROM credit_request_entity WHERE bank_id =?; ";
        String status = null;
        try (var conn = getConnection();
             var statusStmt = conn.prepareStatement(statusSQL)) {
            statusStmt.setString(1, paymentId);
            try (var rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }
}
