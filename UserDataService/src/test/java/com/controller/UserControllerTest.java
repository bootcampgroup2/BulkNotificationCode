package com.controller;
import com.controller.UserController;
import com.exception.CustomException;
import com.model.User;
import com.service.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class UserControllerTest {
	@InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        // Mock the behavior of userService.getAllUsers()
        List<User> userList = new ArrayList<>();
        userList.add(new User("peter", "peter12345", "peter1@example.com", "bombay", "1234567890"));
        userList.add(new User("joe", "joe2345678", "joe2@example.com", "calcutta", "9876543210"));

        when(userService.getAllUsers()).thenReturn(userList);

        // Call the controller method
        ResponseEntity<?> response = userController.getAllUsers();

        // Assert the response status code
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Assert the response body
        List<User> responseBody = (List<User>) response.getBody();
        assertEquals(2, responseBody.size());
        assertEquals("peter", responseBody.get(0).getUserName());
        assertEquals("joe", responseBody.get(1).getUserName());
    }

    @Test
    public void testGetUserFound() throws CustomException {
        String username = "existingUser";
        User testUser = new User(username, "password", "user@example.com", "User Address", "1234567890");

        // Mock the behavior of userService.getUser()
        when(userService.getUser(username)).thenReturn(testUser);

        // Call the controller method
        ResponseEntity<?> response = userController.getUser(username);

        // Assert the response status code
        assertEquals(HttpStatus.FOUND, response.getStatusCode());

        // Assert the response body
        User responseBody = (User) response.getBody();
        assertEquals(username, responseBody.getUserName());
    }

  /*  @Test
    public void testGetUserNotFound() {
        String username = "nonExistingUser";

        // Mock the behavior of userService.getUser() to throw CustomException
        when(userService.getUser(username)).thenThrow(new CustomException("User not found with name: " + username));

        // Call the controller method and expect an exception
        Assertions.assertThrows(CustomException.class, () -> userController.getUser(username));
    }

   */
    
    @Test
    public void testAddUser() {
        User newUser = new User("newUser", "password", "newuser@example.com", "New User Address", "9876543210");

        // Mock the behavior of userService.addUser()
        try {
			Mockito.doNothing().when(userService).addUser(newUser);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Call the controller method
        ResponseEntity<?> response;
		try {
			response = userController.addUser(newUser);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Assert the response status code
        //Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testAddUserWithError() {
        User newUser = new User("existingUser", "password", "existinguser@example.com", "Existing User Address", "5555555555");

        // Mock the behavior of userService.addUser() to throw CustomException
        try {
			doThrow(new CustomException("User already exists")).when(userService).addUser(newUser);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Call the controller method and expect an exception
        Assertions.assertThrows(CustomException.class, () -> userController.addUser(newUser));
    }

    @Test
    public void testDeleteUser() {
        String username = "userToDelete";

        // Mock the behavior of userService.deleteUser()
        doNothing().when(userService).deleteUser(username);

        // Call the controller method
        ResponseEntity<?> response = userController.deleteUser(username);

        // Assert the response status code
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
