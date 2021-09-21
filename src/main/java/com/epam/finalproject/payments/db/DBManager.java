package com.epam.finalproject.payments.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class DBManager {

    private static DBManager instance;

    private DBManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/PaymentsDB");
        } catch (NamingException ex) {
            throw new IllegalStateException("Cannot init DBManager", ex);
        }
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private final DataSource ds;

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


    public static void rollback(Connection con) {
//        if (con != null) {
//            try {
//               // con.rollback();
//            } catch (SQLException e) {
//                e.printStackTrace();
//                //TODO log
//                //TODO throw own ex
//            }
//        }
    }

    public static void commit(Connection con) {
//        if (con != null) {
//            try {
//             //   con.commit();
//            } catch (SQLException e) {
//                e.printStackTrace();
//                // TODO log
//                //TODO throw
//            }
//        }
    }

    public static void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                e.printStackTrace();
                // TODO log
                //TODO throw
            }
        }
    }

    public static void commitAndClose(Connection con) {
        commit(con);
        close(con);
    }

    public static void rollbackAndClose(Connection con) {
        rollback(con);
        close(con);
    }

}
