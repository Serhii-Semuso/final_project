package com.epam.finalproject.payments.db.dao.imp;

import com.epam.finalproject.payments.db.DBManager;
import com.epam.finalproject.payments.db.dao.abstraction.EntityMapper;
import com.epam.finalproject.payments.db.dao.abstraction.PaymentDao;
import com.epam.finalproject.payments.db.entity.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PaymentDaoImp implements PaymentDao {

    private static final String SQL_FIND_PAYMENT_BY_ID = "SELECT * FROM payment WHERE id=?";
    private static final String SQL_FIND_ALL_PAYMENTS = "SELECT * FROM payment";
    private static final String SQL_DELETE_PAYMENT = "DELETE FROM payment WHERE id=?";
    private static final String SQL_FIND_ALL_PAYMENTS_BY_ACCOUNT_ID = "SELECT * FROM payment WHERE account_id_from=?";

    private static final String SQL_FIND_ALL_PAYMENTS_BY_USER_ID = "SELECT * FROM payment " +
            "WHERE account_id_from " +
            "IN (SELECT id FROM `account` WHERE user_id=?)";

    private static final String SQL_INSERT_PAYMENT =
            "INSERT INTO payment(amount, number, description,  account_id_from, account_id_to, payment_status_id)" +
                    " VALUES(?,?,?,?,?,?)";

    private static final String SQL_UPDATE_PAYMENT =
            "UPDATE payment " +
                    "SET amount=?, description=?, sent_date=?, account_id_from=?, account_id_to=?, payment_status_id=? " +
                    "WHERE id=?";

    @Override
    public boolean insert(Payment payment) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_INSERT_PAYMENT);
            int k = 0;
            statement.setBigDecimal(++k, payment.getAmount());
            statement.setString(++k, payment.getNumber());
            statement.setString(++k, payment.getDescription());
            statement.setLong(++k, payment.getAccountIdFrom());
            statement.setLong(++k, payment.getAccountIdTo());
            statement.setLong(++k, payment.getPaymentStatusId());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            //TODO log
            e.printStackTrace();
            DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(statement);
            DBManager.commitAndClose(con);
        }
        return false;
    }

    @Override
    public Payment findById(Long id) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Payment payment = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_FIND_PAYMENT_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                payment = new PaymentMapper().mapRow(resultSet);
            }
        } catch (SQLException e) {
            //TODO log
            DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(resultSet);
            DBManager.close(statement);
            DBManager.commitAndClose(con);
        }
        return payment;
    }

    @Override
    public boolean update(Payment payment) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_UPDATE_PAYMENT);
            int k = 0;
            statement.setBigDecimal(++k, payment.getAmount());
            statement.setString(++k, payment.getDescription());
            statement.setDate(++k, (Date) payment.getSentDate());
            statement.setLong(++k, payment.getAccountIdFrom());
            statement.setLong(++k, payment.getAccountIdTo());
            statement.setLong(++k, payment.getPaymentStatusId());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            //TODO log
            DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(statement);
            DBManager.commitAndClose(con);
        }
        return false;
    }

    @Override
    public boolean delete(Payment payment) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_DELETE_PAYMENT);
            statement.setLong(1, payment.getId());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            //TODO log
            DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(statement);
            DBManager.commitAndClose(con);
        }
        return false;
    }

    @Override
    public Collection<Payment> findAll() {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Payment> payments = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_FIND_ALL_PAYMENTS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                payments.add(new PaymentMapper().mapRow(resultSet));
            }
        } catch (SQLException e) {
            //TODO log
            DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(resultSet);
            DBManager.close(statement);
            DBManager.commitAndClose(con);
        }
        return payments;
    }

    @Override
    public Collection<Payment> findByAccountId(Long id) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Payment> payments = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_FIND_ALL_PAYMENTS_BY_ACCOUNT_ID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                payments.add(new PaymentMapper().mapRow(resultSet));
            }
        } catch (SQLException e) {
            //TODO log
            DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(resultSet);
            DBManager.close(statement);
            DBManager.commitAndClose(con);
        }
        return payments;
    }

    @Override
    public Collection<Payment> findByUserId(Long id) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Payment> payments = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_FIND_ALL_PAYMENTS_BY_USER_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                payments.add(new PaymentMapper().mapRow(resultSet));
            }
        } catch (SQLException e) {
            //TODO log
            DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(resultSet);
            DBManager.close(statement);
            DBManager.commitAndClose(con);
        }
        return payments;
    }

    private static class PaymentMapper implements EntityMapper<Payment> {

        @Override
        public Payment mapRow(ResultSet resultSet) {
            Payment payment = new Payment();
            try {
                payment.setId(resultSet.getLong(Fields.ENTITY_ID));
                payment.setNumber(resultSet.getString(Fields.PAYMENT_NUMBER));
                payment.setAmount(resultSet.getBigDecimal(Fields.PAYMENT_AMOUNT));
                payment.setDescription(resultSet.getString(Fields.PAYMENT_DESCRIPTION));
                payment.setCreationDate(resultSet.getDate(Fields.PAYMENT_CREATION_DATE));
                payment.setSentDate(resultSet.getDate(Fields.PAYMENT_SENT_DATE));
                payment.setAccountIdFrom(resultSet.getLong(Fields.PAYMENT_ACCOUNT_ID_FROM));
                payment.setAccountIdTo(resultSet.getLong(Fields.PAYMENT_ACCOUNT_ID_TO));
                payment.setPaymentStatusId(resultSet.getLong(Fields.PAYMENT_PAYMENT_STATUS_ID));
                return payment;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IllegalStateException("Cannot map payment row.", e);
            }
        }

    }
}
