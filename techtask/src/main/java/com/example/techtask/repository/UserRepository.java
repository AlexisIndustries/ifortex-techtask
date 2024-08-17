package com.example.techtask.repository;

import com.example.techtask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT u.* FROM users u " +
            "JOIN orders o ON u.id = o.user_id " +
            "WHERE o.order_status = 'DELIVERED' AND EXTRACT(YEAR FROM o.created_at) = 2003 " +
            "GROUP BY u.id " +
            "ORDER BY SUM(o.price * o.quantity) DESC " +
            "LIMIT 1", nativeQuery = true)
    User findUserWithMaxDeliveredOrderSumIn2003();

    @Query(value = "SELECT DISTINCT u.* FROM users u " +
            "JOIN orders o ON u.id = o.user_id " +
            "WHERE o.order_status = 'PAID' AND EXTRACT(YEAR FROM o.created_at) = 2010",
            nativeQuery = true)
    List<User> findUsersWithPaidOrdersIn2010();
}
