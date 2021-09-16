package com.epam.finalproject.payments.db.dao.imp;

import com.epam.finalproject.payments.db.DBManager;
import com.epam.finalproject.payments.db.dao.abstraction.EntityMapper;
import com.epam.finalproject.payments.db.dao.abstraction.UserDao;
import com.epam.finalproject.payments.db.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDaoImp implements UserDao {

    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?";
    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM user ORDER BY id";
    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE id=?";

    private static final String SQL_INSERT_USER =
            "INSERT INTO user(login, password, role_id, first_name, last_name, email, phone_number, is_blocked)" +
                    " VALUES(?,?,?,?,?,?,?,?)";

    private static final String SQL_UPDATE_USER =
            "UPDATE user SET login=?, password=?, role_id=?, " +
                    "first_name=?, last_name=?, email=?, phone_number=?, is_blocked=? WHERE id=?";


    @Override
    public boolean insert(User user) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_INSERT_USER);
            int k = 0;
            statement.setString(++k, user.getLogin());
            statement.setString(++k, user.getPassword());
            statement.setLong(++k, user.getRoleId());
            statement.setString(++k, user.getFirstName());
            statement.setString(++k, user.getLastName());
            statement.setString(++k, user.getEmail());
            statement.setString(++k, user.getPhoneNumber());
            statement.setBoolean(++k, user.getBlocked());
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
    public User findById(Long id) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_FIND_USER_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new UserMapper().mapRow(resultSet);
            }
        } catch (SQLException e) {
            //TODO log
            DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(resultSet);
            DBManager.close(statement);
            DBManager.commitAndClose(con);
        }
        return user;
    }

    @Override
    public boolean update(User user) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_UPDATE_USER);
            int k = 0;
            statement.setString(++k, user.getLogin());
            statement.setString(++k, user.getPassword());
            statement.setLong(++k, user.getRoleId());
            statement.setString(++k, user.getFirstName());
            statement.setString(++k, user.getLastName());
            statement.setString(++k, user.getEmail());
            statement.setString(++k, user.getPhoneNumber());
            statement.setBoolean(++k, user.getBlocked());
            statement.setLong(++k, user.getId());
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
    public boolean delete(User user) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_DELETE_USER);
            statement.setLong(1, user.getId());
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
    public Collection<User> findAll() {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_FIND_ALL_USERS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new UserMapper().mapRow(resultSet));
            }
        } catch (SQLException e) {
            //TODO log
            DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(resultSet);
            DBManager.close(statement);
            DBManager.commitAndClose(con);
        }
        return users;
    }

    @Override
    public User findByLogin(String login) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            con = DBManager.getInstance().getConnection();
            statement = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new UserMapper().mapRow(resultSet);
            }
        } catch (SQLException e) {
            //TODO log
            DBManager.rollbackAndClose(con);
        } finally {
            DBManager.close(resultSet);
            DBManager.close(statement);
            DBManager.commitAndClose(con);
        }
        return user;
    }

    private static class UserMapper implements EntityMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet) {
            User user = new User();
            try {
                user.setId(resultSet.getLong(Fields.ENTITY_ID));
                user.setLogin(resultSet.getString(Fields.USER_LOGIN));
                user.setPassword(resultSet.getString(Fields.USER_PASSWORD));
                user.setRoleId(resultSet.getLong(Fields.USER_ROLE_ID));
                user.setFirstName(resultSet.getString(Fields.USER_FIRST_NAME));
                user.setLastName(resultSet.getString(Fields.USER_LAST_NAME));
                user.setEmail(resultSet.getString(Fields.USER_EMAIL));
                user.setPhoneNumber(resultSet.getString(Fields.USER_PHONE_NUMBER));
                user.setBlocked(resultSet.getBoolean(Fields.USER_IS_BLOCKED));
                return user;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IllegalStateException("Cannot map user row.", e);
            }
        }
    }

}
