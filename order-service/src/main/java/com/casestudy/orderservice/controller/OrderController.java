package com.casestudy.orderservice.controller;


import com.casestudy.basedomain.dto.Order;
import com.casestudy.basedomain.dto.OrderEvent;
import com.casestudy.orderservice.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
	
	
	/*public String[] sendEmailIds() {
		String[] emailIds = new String[3];
		emailIds[0] = "janhavimudaliar@gmail.com";
		emailIds[1] = "mudaliarlata71@gmail.com";
		emailIds[2] = "r3550510@gmail.com";
		
		
		return emailIds;
		}*/
    private OrderProducer orderProducer;
    
    
    String[] emailIds ={"janhavimudaliar@gmail.com","mudaliarlata71@gmail.com","r3550510@gmail.com"};
	//emailIds[0] = "janhavimudaliar@gmail.com";
	//emailIds[1] = "mudaliarlata71@gmail.com";
	//emailIds[2] = "r3550510@gmail.com";
	

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }
    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order){
    	
    	for(int i=0;i<3;i++)
    	{
    		 order.setOrderId((UUID.randomUUID().toString()));

    	        OrderEvent orderEvent = new OrderEvent();
    	        orderEvent.setStatus("Pending");
    	        orderEvent.setMessage("order status is in pending state");
    	        orderEvent.setOrder((order));
    	        orderEvent.setEmail(emailIds[i]);
    	        
    	        orderEvent.setIsInstantEmail(true);

    	        

    	        orderProducer.sendMessage((orderEvent));

    	}
    	/*
        order.setOrderId((UUID.randomUUID().toString()));

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("Pending");
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setOrder((order));
        orderEvent.setEmail(sendEmailIds());
        
        orderEvent.setIsInstantEmail(true);

        

        orderProducer.sendMessage((orderEvent));
*/
        return "Order Placed successfully";


    }
}
