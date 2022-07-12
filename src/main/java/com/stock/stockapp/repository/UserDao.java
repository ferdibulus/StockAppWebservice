package com.stock.stockapp.repository;

import com.stock.stockapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<Users,Integer> {
}
