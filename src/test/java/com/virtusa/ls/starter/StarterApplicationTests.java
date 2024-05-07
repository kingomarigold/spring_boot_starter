package com.virtusa.ls.starter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StarterApplicationTests {

	@Test
	void contextLoads() {
	    // Just to validate that the application context loads.
	}

}
