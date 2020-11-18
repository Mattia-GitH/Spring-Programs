package com.SpringH2DB.SpringH2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Pattern;

@SpringBootTest
class SpringH2ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void numberOfColumns() {
		String columns = "name,surname,email,password";
		String[] splitColumns = columns.split(",");
		Assertions.assertEquals(4, splitColumns.length);
	}

	@Test
	void castTest() {
		int castDouble = (int) Math.sqrt(24);
		boolean bool = true;
		if (castDouble != 4) {
			bool = false;
		}
		Assertions.assertTrue(bool);
	}

	@Test
	void passwordCheck() {
		String password = "passw0rd";
		boolean valid = false;
		valid = Pattern.compile("[0-9]").matcher(password).find();

		Assertions.assertTrue(valid);
	}

	@Test
	void randomAlwaysTrue() {
		double generateNumber = Math.random() * 10;
		int casualNumber = (int) generateNumber;
		while (casualNumber != 5) {
			generateNumber = Math.random() * 10;
			casualNumber = (int) generateNumber;
		}
		Assertions.assertEquals(5, casualNumber);
	}
}




