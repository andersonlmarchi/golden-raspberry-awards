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
				.andExpect(content().json("{\"min\":[{\"interval\":6,\"producer\":\"Bo Derek\",\"previousWin\":1984,\"followingWin\":1990}],\"max\":[{\"interval\":6,\"producer\":\"Bo Derek\",\"previousWin\":1984,\"followingWin\":1990}]}"));

	}

}
