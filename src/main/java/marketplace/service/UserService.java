package marketplace.service;

import marketplace.entity.User;

public interface UserService {
    public void saveUser(User user);
    public User findById(String id) throws Exception;
}
