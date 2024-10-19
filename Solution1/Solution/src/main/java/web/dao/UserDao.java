package web.dao;

import web.model.User;
import java.util.List;

public interface UserDao {
    User getUser(int id);
    List<User> getUsers();
    void saveUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
}