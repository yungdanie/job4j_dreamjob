package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Repository @ThreadSafe
public class UserDbStore {

    private final BasicDataSource pool;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDbStore.class.getName());

    public UserDbStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Collection<User> findAll() {
        Collection<User> collection = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement pr = cn.prepareStatement("select * from users")) {
            try (ResultSet resultSet = pr.executeQuery()) {
                while (resultSet.next()) {
                    collection.add(new User(resultSet.getInt("id"), resultSet.getString("email"),
                            resultSet.getString("password")));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error:", e);
        }
        return collection;
    }

    public User add(User user) {
        try (Connection cn = pool.getConnection();
             PreparedStatement pr = cn.prepareStatement("INSERT INTO users(email, password) VALUES (?, ?)")) {
             pr.setString(1, user.getEmail());
             pr.setString(2, user.getPassword());
             pr.execute();
             try (ResultSet resultSet = pr.getGeneratedKeys()) {
                 if (resultSet.next()) {
                     user.setId(resultSet.getInt(1));
                 }
             }
        } catch (SQLException e) {
            LOGGER.error("Error:", e);
            return null;
        }
        return user;
    }

    public User findById(int id) {
        try (Connection cn = pool.getConnection()) {
            try (PreparedStatement pr = cn.prepareStatement("select * from users where id = ?")) {
                pr.setInt(1, id);
                try (ResultSet result = pr.executeQuery()) {
                    if (result.next()) {
                        return new User(result.getInt("id"), result.getString("email"),
                                result.getString("password"));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error:", e);
        }
        return null;
    }
}
