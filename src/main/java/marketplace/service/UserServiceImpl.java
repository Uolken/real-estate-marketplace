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
    @Autowired
    UserServiceImpl(UserRepos userRepos){
        this.userRepos = userRepos;
    }
}
