package com.GoFit.DietPlan;

import com.GoFit.DietPlan.Repositories.DietPlanRepository;
import com.GoFit.DietPlan.Repositories.UserRepository;
import com.GoFit.DietPlan.Repositories.PhysicalConditionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class DietPlanApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	UserRepository userRepository;

	@Autowired
	DietPlanRepository dietPlanRepository;

	@Autowired
	PhysicalConditionRepository physicalConditionRepository;

	@Autowired
	private ObjectMapper objectMapper; //for mapping to JSON and from JSON

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}




}
