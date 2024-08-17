package com.example.techtask.service.impl;

import com.example.techtask.model.Order;
import com.example.techtask.model.enumiration.UserStatus;
import com.example.techtask.repository.OrderRepository;
import com.example.techtask.repository.UserRepository;
import com.example.techtask.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findOrder() {
//        Optional<Order> optionalOrder = orderRepository.findAll()
//                .stream()
//                .filter(order -> order.getQuantity() > 1)
//                .max(Comparator.comparing(Order::getCreatedAt));

        return orderRepository.findNewestOrderWithQuantityMoreThanOne();
    }

    @Override
    public List<Order> findOrders() {
//        return userRepository.findAll().stream()
//                .filter(user -> user.getUserStatus() == UserStatus.ACTIVE)
//                .flatMap(user -> user.getOrders().stream())
//                .sorted(Comparator.comparing(Order::getCreatedAt))
//                .collect(Collectors.toList());
        return orderRepository.findOrdersFromActiveUsersSortedByDate();
    }
}
