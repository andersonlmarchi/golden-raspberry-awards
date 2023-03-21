package com.awards.films;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class GoldenRaspberryAwardsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getProducersWithMinAndMaxAwardsInterval() throws Exception {
		mockMvc.perform(get("/api/raspberry/")
						.accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json("{\"min\":[{\"interval\":1,\"producer\":\"Joel Silver\",\"previousWin\":1990,\"followingWin\":1991}],\"max\":[{\"interval\":13,\"producer\":\"Matthew Vaughn\",\"previousWin\":2002,\"followingWin\":2015}]}"));

	}

}
