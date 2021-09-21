package com.epam.finalproject.payments.db.dao.imp;

import com.epam.finalproject.payments.db.DBManager;
import com.epam.finalproject.payments.db.dao.abstraction.AccountDao;
import com.epam.finalproject.payments.db.dao.abstraction.EntityMapper;
import com.epam.finalproject.payments.db.entity.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccountDaoImp implements AccountDao {

    private static final String SQL_FIND_ACCOUNT_BY_ID = "SELECT * FROM account WHERE id=?";
    private static final String SQL_FIND_ALL_ACCOUNTS = "SELECT * FROM account";
    private static final String SQL_FIND_ALL_ACCOUNTS_BY_USER_ID = "SELECT * FROM account WHERE user_id=?";
    private static final String SQL_DELETE_ACCOUNT = "DELETE FROM account WHERE id=?";

    private static final String SQL_INSERT_ACCOUNT =
            "INSERT INTO account(`name`, `number`, user_id) VALUES(?,?, ?)";

    private static final String SQL_UPDATE_ACCOUNT =
            "UPDATE account SET `name`=?, `balance`=?, is_blocked=?, unblock_request=? WHERE id=?";

    @Override
    public boolean insert(Account account) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_INSERT_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
            int k = 0;
            statement.setString(++k, account.getName());
            statement.setString(++k, account.getNumber());
            statement.setLong(++k, account.getUserId());

            if (statement.executeUpdate() != 0) {
                rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    account.setId(rs.getLong(1));
                }
                return true;
            }
        } catch (SQLException e) {
            //TODO log
            DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(rs);
            DBManager.close(statement);
            DBManager.commitAndClose(con);
        }
        return false;
    }

    @Override
    public Account findById(Long id) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Account account = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_FIND_ACCOUNT_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                account = new AccountMapper().mapRow(resultSet);
            }
        } catch (SQLException e) {
            //TODO log
            DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(resultSet);
            DBManager.close(statement);
            DBManager.commitAndClose(con);
        }
        return account;
    }

    @Override
    public boolean update(Account account) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_UPDATE_ACCOUNT);
            int k = 0;
            statement.setString(++k, account.getName());
            statement.setBigDecimal(++k, account.getBalance());
            statement.setBoolean(++k, account.getBlocked());
            statement.setBoolean(++k, account.getUnblockRequest());
            statement.setLong(++k, account.getId());
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
    public boolean delete(Account account) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_DELETE_ACCOUNT);
            statement.setLong(1, account.getId());
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
    public Collection<Account> findAll() {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Account> accounts = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_FIND_ALL_ACCOUNTS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accounts.add(new AccountMapper().mapRow(resultSet));
            }
        } catch (SQLException e) {
            //TODO log
            DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(resultSet);
            DBManager.close(statement);
            DBManager.commitAndClose(con);
        }
        return accounts;
    }

    @Override
    public Collection<Account> findByUserId(Long id) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Account> accounts = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_FIND_ALL_ACCOUNTS_BY_USER_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accounts.add(new AccountMapper().mapRow(resultSet));
            }
        } catch (SQLException e) {
            //TODO log
            DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(resultSet);
            DBManager.close(statement);
            DBManager.commitAndClose(con);
        }
        return accounts;
    }

    private static class AccountMapper implements EntityMapper<Account> {

        @Override
        public Account mapRow(ResultSet resultSet) {
            Account account = new Account();
            try {
                account.setId(resultSet.getLong(Fields.ENTITY_ID));
                account.setName(resultSet.getString(Fields.ACCOUNT_NAME));
                account.setNumber(resultSet.getString(Fields.ACCOUNT_NUMBER));
                account.setBalance(resultSet.getBigDecimal(Fields.ACCOUNT_BALANCE));
                account.setCreationDate(resultSet.getDate(Fields.ACCOUNT_CREATION_DATE));
                account.setUserId(resultSet.getLong(Fields.ACCOUNT_USER_ID));
                account.setBlocked(resultSet.getBoolean(Fields.ACCOUNT_IS_BLOCKED));
                account.setUnblockRequest(resultSet.getBoolean(Fields.ACCOUNT_UNBLOCK_REQUEST));
                return account;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IllegalStateException("Cannot map account row.", e);
            }
        }
    }
}
