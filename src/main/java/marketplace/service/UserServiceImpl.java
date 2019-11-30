package marketplace.service;

import marketplace.entity.User;
import marketplace.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepos userRepos;

    @Override
    public void saveUser(User user) {
        userRepos.save(user);
    }

    @Override
    public User findById(String id) throws Exception {
        return userRepos.findById(id).orElseThrow(Exception::new);
    }

    @Autowired
    UserServiceImpl(UserRepos userRepos){
        this.userRepos = userRepos;
    }
}
