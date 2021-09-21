package com.epam.finalproject.payments.db.dao.imp;

import com.epam.finalproject.payments.db.DBManager;
import com.epam.finalproject.payments.db.dao.abstraction.CreditCardDao;
import com.epam.finalproject.payments.db.dao.abstraction.EntityMapper;
import com.epam.finalproject.payments.db.entity.CreditCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CreditCardDaoImp implements CreditCardDao {

    private static final String SQL_FIND_CREDIT_CARD_BY_ID = "SELECT * FROM credit_card WHERE id=?";
    private static final String SQL_FIND_ALL_CARDS = "SELECT * FROM credit_card ORDER BY id";
    private static final String SQL_DELETE_CREDIT_CARD = "DELETE FROM credit_card WHERE id=?";

    private static final String SQL_INSERT_CREDIT_CARD =
            "INSERT INTO credit_card(`limit`, account_id)" +
                    " VALUES(?,?)";

    private static final String SQL_UPDATE_CREDIT_CARD =
            "UPDATE credit_card SET `limit`=?, account_id=? WHERE id=?";


    @Override
    public boolean insert(CreditCard card) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_INSERT_CREDIT_CARD);
            int k = 0;
            statement.setBigDecimal(++k, card.getLimit());
            statement.setLong(++k, card.getAccountId());
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
    public CreditCard findById(Long id) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        CreditCard card = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_FIND_CREDIT_CARD_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                card = new CreditCardMapper().mapRow(resultSet);
            }
        } catch (SQLException e) {
            //TODO log
            DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(resultSet);
            DBManager.close(statement);
            DBManager.commitAndClose(con);
        }
        return card;
    }

    @Override
    public boolean update(CreditCard card) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_UPDATE_CREDIT_CARD);
            int k = 0;
            statement.setBigDecimal(++k, card.getLimit());
            statement.setLong(++k, card.getAccountId());
            statement.setLong(++k, card.getId());
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
    public boolean delete(CreditCard card) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_DELETE_CREDIT_CARD);
            statement.setLong(1, card.getId());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            //TODO log
            // DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(statement);
            DBManager.close(con);
            //  DBManager.commitAndClose(con);
        }
        return false;
    }

    @Override
    public Collection<CreditCard> findAll() {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CreditCard> cards = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_FIND_ALL_CARDS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cards.add(new CreditCardMapper().mapRow(resultSet));
            }
        } catch (SQLException e) {
            //TODO log
            DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(resultSet);
            DBManager.close(statement);
            DBManager.commitAndClose(con);
        }
        return cards;
    }


    private static class CreditCardMapper implements EntityMapper<CreditCard> {

        @Override
        public CreditCard mapRow(ResultSet resultSet) {
            CreditCard card = new CreditCard();
            try {
                card.setId(resultSet.getLong(Fields.ENTITY_ID));
                card.setNumber(resultSet.getString(Fields.CREDIT_CARD_NUMBER));
                card.setLimit(resultSet.getBigDecimal(Fields.CREDIT_CARD_LIMIT));
                card.setAccountId(resultSet.getLong(Fields.CREDIT_CARD_ACCOUNT_ID));
                return card;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IllegalStateException("Cannot map credit card row.", e);
            }
        }
    }

}
