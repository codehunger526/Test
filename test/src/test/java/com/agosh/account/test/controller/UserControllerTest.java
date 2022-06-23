package com.agosh.account.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.agosh.account.test.entity.UserEntity;
import com.agosh.account.test.exception.RecordNotFoundException;
import com.agosh.account.test.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	public void getUserReturnUser() throws Exception {

		given(userService.getUserById(anyLong()))
				.willReturn(new UserEntity("shailendra", "gupta", null, "shailendra3psk@gmail.com"));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getUser/1"))
				.andExpect(status().isOk()).andExpect(jsonPath("firstName").value("shailendra"))
				.andExpect(jsonPath("lastName").value("gupta"));

	}

	@Test
	public void getUserReturNotFound() throws Exception {
		given(userService.getUserById(anyLong()))
				.willThrow(new RecordNotFoundException("Not found"));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getUser/2"))
				.andExpect(status().isNotFound());

	}

}
