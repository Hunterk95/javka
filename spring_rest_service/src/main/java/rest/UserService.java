package rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> readAll() {
        return userRepository.findAll();
    }

    public void create(User user) {
        userRepository.save(user);
    }

    public boolean update(User user, int id) {
        if (user.getUserId() == id){
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public User read(int id){
        return userRepository.findById(id).get();
    }

    public boolean delete(int id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
