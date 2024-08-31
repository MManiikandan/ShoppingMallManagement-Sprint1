package com.tnsif.sm.orderitem;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
	
	@Autowired
	 private OrderItemRepository repo;

	public OrderItem save(OrderItem orderItem) {
		return repo.save(orderItem);
	}

	public List<OrderItem> findAll() {
		
		return repo.findAll();
	}

	public void deleteById(int id) {
		repo.deleteById(id);
	}

	
	public Optional<OrderItem> getById(int id) {

		return repo.findById(id);
	}

	public void deleteOrderItem(int id) {
		repo.deleteById(id);
	}

	

	
}
