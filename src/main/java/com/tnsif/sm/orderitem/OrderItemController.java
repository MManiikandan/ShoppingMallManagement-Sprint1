package com.tnsif.sm.orderitem;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class OrderItemController {
	
	 @Autowired
	 private OrderItemService service;
	 
	 @GetMapping("/api/orderitems")
	 public List<OrderItem> getOrderItems(){
		 return service.findAll();
	 }
	 
	 @GetMapping("/api/orderitem/{orderItemId}")
	 public Optional<OrderItem> getOrderItem(@PathVariable("orderItemId") int id) {
		 return service.getById(id);
	 }
	 @PostMapping("/api/postorderitem")
	 public  ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem){
		return ResponseEntity.ok(service.save(orderItem));
	 }
	
	
	 @PutMapping("/api/update/{orderItemId}")
	 public ResponseEntity<?> updateOrderItem(@PathVariable("orderItemId") int id, @RequestBody OrderItem orderItemDetails) {
	        return service.getById(id).map(orderItem -> {
	        	  if (orderItemDetails.getOrderId() != null) {
	                  orderItem.setOrderId(orderItemDetails.getOrderId());
	              }
	              if (orderItemDetails.getProductId() != null) {
	                  orderItem.setProductId(orderItemDetails.getProductId());
	              }
	              if (orderItemDetails.getQuanity() != null) {
	                  orderItem.setQuanity(orderItemDetails.getQuanity());
	              }
	              if (orderItemDetails.getUnitPrice() != 0.0) {
	                  orderItem.setUnitPrice(orderItemDetails.getUnitPrice());
	              }
                service.save(orderItem);
                return new ResponseEntity<>("OrderItem updated successfully", HttpStatus.OK);
	            }).orElseGet(() -> {
	            String errorMessage = "Invalid orderItemId: " + id;
	            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	           });
	               
	    }
	    @DeleteMapping("/api/delete/{orderItemId}")
	    public ResponseEntity<?> deleteOrderItem(@PathVariable("orderItemId") int id) {
	        return service.getById(id).map(orderItem -> {
	            service.deleteOrderItem(id);
	            return new ResponseEntity<>("OrderItem deleted successfully", HttpStatus.OK);
	        }).orElseGet(() -> {
	            String errorMessage = "Invalid orderItemId: " + id;
	            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	        });
	    }
	  
}
 