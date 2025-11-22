package com.interview_test.demo.service;

import com.interview_test.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserServiceImpl implements UserService{

    private final Map<Long, User> userMap = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public List<User> getAllUsers(){
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User getUser(Long id){
        return userMap.get(id);
    }

    @Override
    public User saveUser(User user){
        long newId = idGenerator.incrementAndGet();
        user.setId(newId);
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(Long id, User user){
        if (!userMap.containsKey(id)) {
            return null;
        }
        user.setId(id);
        userMap.put(id, user);
        return user;
    }

    @Override
    public User deleteUser(Long id){
        return userMap.remove(id);
    }
}
