package com.stock.stockapp.service;

import com.stock.stockapp.entity.Users;
import com.stock.stockapp.repository.ProductDao;
import com.stock.stockapp.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<Users> getListOfUsers() {
        return userDao.findAll();
    }

    public void save(Users user) {
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        userDao.save(user);
    }

    public Optional<Users> findById(Integer id) {
        return userDao.findById(id);
    }

    public void deleteById(Integer id) {
        userDao.deleteById(id);
    }

    public List<Users> findUser(Users user) {
       return userDao.findAll();
    }
}
