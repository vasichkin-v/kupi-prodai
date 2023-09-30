package com.example.kupiprodai;

import com.example.kupiprodai.models.User;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class KupiProdaiApplicationTests {
//	@BeforeAll
	private static void before() {
	}

	@Test
	void contextLoads() {
	}

	@Test
	@WithMockUser(username = "john", roles = { "VIEWER" })
	public void checkAuthUserName() {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
//		String role = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities().stream().findFirst().get();
		//todo еще проверить роль
		assertEquals("john", userName);
	}

}
