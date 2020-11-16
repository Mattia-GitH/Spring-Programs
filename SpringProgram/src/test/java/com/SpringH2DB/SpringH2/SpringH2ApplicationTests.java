package com.SpringH2DB.SpringH2;

import com.SpringH2DB.SpringH2.Convert.BookConvert;
import com.SpringH2DB.SpringH2.entity.BookEntity;
import com.SpringH2DB.SpringH2.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Pattern;

//@SpringBootTest
class SpringH2ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void numberOfColumns() {
		String columns = "name,surname,email,password";
		String splitColumns[] = columns.split(",");
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


	//Conversions: Model To Entity - Entity To Model
	@Test
	void converterToModel() {
		BookEntity bookEntity = new BookEntity();
		bookEntity.setAuthor("Autore");
		bookEntity.setTitle("Titolo");
		bookEntity.setIsbn("isbn");

		String s1 = bookEntity.toString();
		String s2 = s1.substring(s1.indexOf("{"));
		String converted = BookConvert.toBookModel(bookEntity).toString();
		String converted2 = converted.substring(converted.indexOf("{"));

		Assertions.assertEquals(s2, converted2);
	}

	@Test
	void converterToEntity() {
		Book book = new Book();
		book.setAuthor("Autore");
		book.setTitle("Titolo");
		book.setIsbn("isbn");

		String s1 = book.toString();
		String s2 = s1.substring(s1.indexOf("{"));
		String converted = BookConvert.toBookEntity(book).toString();
		String converted2 = converted.substring(converted.indexOf("{"));

		Assertions.assertEquals(s2, converted2);
	}



}




