package com.se.team00.demo;

import com.se.team00.demo.Entity.Student;
import com.se.team00.demo.Repository.StudentRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.OptionalInt;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
public class DemoApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TestEntityManager entityManager;

	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testStudentFirstNameCannotBeNull() {
		Student s = new Student();
		s.setFirstName(null);
		s.setLastName("sd");
		s.setStudentId("B5900000");

		try {
			entityManager.persist(s);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testStudentLastNameCannotBeNull() {
		Student s = new Student();
		s.setFirstName("abc");
		s.setLastName(null);
		s.setStudentId("B5900000");

		try {
			entityManager.persist(s);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testIdMustStartByBMD() {
		Student s = new Student();
		s.setFirstName("abd");
		s.setLastName("abd");
		s.setStudentId("F5900000");

		try {
			entityManager.persist(s);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testIdLength() {
		Student s = new Student();
		s.setFirstName("abd");
		s.setLastName("abd");
		s.setStudentId("B59000000");

		try {
			entityManager.persist(s);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testIdMustBeInteger() {
		Student s = new Student();
		s.setFirstName("abd");
		s.setLastName("abd");
		s.setStudentId("Basdfghj");

		try {
			entityManager.persist(s);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test(expected=javax.persistence.PersistenceException.class)
	public void testIdMustBeUnique() {
		Student s1 = new Student();
		s1.setFirstName("Abcd");
		s1.setLastName("Abcd");
		s1.setStudentId("B5900000");
		entityManager.persist(s1);
		entityManager.flush();

		Student s2 = new Student();
		s2.setFirstName("Defg");
		s2.setLastName("Defg");
		s2.setStudentId("B5900000");

		entityManager.persist(s2);
		entityManager.flush();

		fail("Should not pass to this line");
	}

}


