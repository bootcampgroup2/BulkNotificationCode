package com.casestudy.orderservice.controller;

	
	import org.junit.jupiter.api.Test;
	import org.mockito.Mock;
	import org.mockito.Mockito;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.boot.test.mock.mockito.MockBean;
	import org.springframework.http.MediaType;
	import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
	import org.springframework.test.web.servlet.MockMvc;
	import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
	import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

	import com.casestudy.basedomain.dto.Order;
	import com.casestudy.basedomain.dto.OrderEvent;
	import com.casestudy.orderservice.kafka.OrderProducer;
	import com.fasterxml.jackson.databind.ObjectMapper;

	import java.util.UUID;

	@SpringBootTest
	@AutoConfigureMockMvc
	@SpringJUnitConfig
	public class OrderControllerTest {

	    @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private OrderProducer orderProducer;

	    @Test
	    public void testPlaceOrder() throws Exception {
	        // Create a sample Order object
	        Order order = new Order();
	        order.setOrderId(UUID.randomUUID().toString());

	        // Mock the behavior of orderProducer
	        OrderEvent orderEvent = new OrderEvent();
	        Mockito.doNothing().when(orderProducer).sendMessage(Mockito.any(OrderEvent.class));

	        // Perform a POST request to the /api/v1/orders endpoint
	        mockMvc.perform(MockMvcRequestBuilders
	                .post("/api/v1/orders")
	                .content(asJsonString(order))
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.content().string("Order Placed successfully"));
	    }

	    // Helper method to convert an object to JSON string
	    private static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	}


