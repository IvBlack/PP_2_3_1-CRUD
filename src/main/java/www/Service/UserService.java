package www.Service;

import www.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User getUser(int id);
    public void updateUser(int id, User user);

    public void saveUser(User user);
    public void deleteUser(int id);
}
