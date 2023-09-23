package com.casestudy.emailservice.controller;

	import com.casestudy.emailservice.controller.MessageController;
	import com.casestudy.emailservice.kafka.MessageService;
	import com.casestudy.emailservice.models.Ordermail;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.mockito.InjectMocks;
	import org.mockito.Mock;
	import org.mockito.MockitoAnnotations;
	import org.springframework.http.MediaType;
	import org.springframework.mock.web.MockHttpServletRequest;
	import org.springframework.mock.web.MockHttpServletResponse;
	import org.springframework.test.web.servlet.MockMvc;
	import org.springframework.test.web.servlet.MvcResult;
	import org.springframework.test.web.servlet.RequestBuilder;
	import org.springframework.test.web.servlet.ResultMatcher;
	import org.springframework.test.web.servlet.setup.MockMvcBuilders;

	import java.util.Arrays;
	import java.util.List;

	import static org.mockito.Mockito.*;
	import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

	public class MessageControllerTest {

	    @InjectMocks
	    private MessageController messageController;

	    @Mock
	    private MessageService messageService;

	    private MockMvc mockMvc;

	    @BeforeEach
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
	    }

	    @Test
	    public void testGetUnreadMessages() throws Exception {
	        // Mock data
	        String email = "test@example.com";
	        List<Ordermail> mockUnreadMessages = Arrays.asList(
	            new Ordermail("Subject 1", "Body 1",  false),
	            new Ordermail("Subject 2", "Body 2",  false)
	        );

	        // Mock the service method
	        when(messageService.getUnreadMessagesByEmail(email)).thenReturn(mockUnreadMessages);

	        // Prepare the GET request
	        RequestBuilder requestBuilder = get("/api/message/{email}", email)
	            .accept(MediaType.APPLICATION_JSON);

	        // Define the expected result
	        ResultMatcher resultMatcher = mvcResult -> {
	            MockHttpServletResponse response = mvcResult.getResponse();
	            String jsonResponse = response.getContentAsString();
	            // Add assertions here to verify the response content and status code as needed.
	        };

	        // Perform the GET request and validate the response
	        MvcResult result = mockMvc.perform(requestBuilder)
	            .andExpect(resultMatcher)
	            .andReturn();

	        // Verify that the service method was called with the correct email
	        verify(messageService, times(1)).getUnreadMessagesByEmail(email);
	        verifyNoMoreInteractions(messageService);
	    }
	}


