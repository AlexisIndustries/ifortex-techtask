package com.example.techtask.service.impl;

import com.example.techtask.model.Order;
import com.example.techtask.model.User;
import com.example.techtask.model.enumiration.OrderStatus;
import com.example.techtask.repository.OrderRepository;
import com.example.techtask.repository.UserRepository;
import com.example.techtask.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public UserServiceImpl(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public User findUser() {
        double maxSum = 0;
        int userId = 0;

        Optional<Order> order = orderRepository.findAll()
                .stream()
                .filter(o -> o.getCreatedAt().getYear() == 2003)
                .max(Comparator.comparingDouble(o -> o.getQuantity() * o.getPrice()));

        Optional<User> user = Optional.empty();
        if (order.isPresent()) {
           user = userRepository.findById((long) order.get().getUserId());
        }

        return user.orElse(null);
    }

    @Override
    public List<User> findUsers() {

        return orderRepository.findAll()
                .stream()
                .filter(o -> o.getCreatedAt().getYear() == 2010)
                .filter(o -> o.getOrderStatus() == OrderStatus.PAID)
                .map(o -> userRepository.findById((long) o.getUserId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
