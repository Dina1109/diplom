package ru.netology.diplom.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLHelper {

    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    @SneakyThrows
    public static Connection getConn() {
        String dbUrl = System.getProperty("db.url");
        String login = System.getProperty("login");
        String password = System.getProperty("password");
        final Connection connection = DriverManager.getConnection(dbUrl, login, password);
        return connection;
    }

//    @SneakyThrows
//    public static void cleanDatabase() {
//        var conn = getConn();
//        runner.execute(conn, "DELETE FROM payment_entity;");
//        runner.execute(conn, "DELETE FROM credit_request_entity;");
//        runner.execute(conn, "DELETE FROM payment_id;");
//    }

    @SneakyThrows
    public static DataHelper.PaymentId getPaymentId() {
        String idSQL = "SELECT payment_id FROM order_entity order by created DESC;";
        Connection conn = getConn();
        return runner.query(conn, idSQL, new BeanHandler<>(DataHelper.PaymentId.class));
    }

    @SneakyThrows
    public static DataHelper.StatusPayment getStatusPayment() {
        String statusSQL = "SELECT status FROM payment_entity WHERE transaction_id =?; ";
        Connection conn = getConn();
        return runner.query(conn, statusSQL, new BeanHandler<>(DataHelper.StatusPayment.class));
    }

    @SneakyThrows
    public static DataHelper.StatusCredit getStatusCredit() {
        String statusSQL = "SELECT status FROM credit_request_entity WHERE bank_id =?; ";
        Connection conn = getConn();
        return runner.query(conn, statusSQL, new BeanHandler<>(DataHelper.StatusCredit.class));
    }

}
