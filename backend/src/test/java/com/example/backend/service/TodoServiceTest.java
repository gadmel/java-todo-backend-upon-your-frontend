package com.example.backend.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import com.example.backend.model.Todo;
import com.example.backend.model.Status;
import com.example.backend.repository.TodoRepo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Nested
@DisplayName("TodoService Unit Testing")
class TodoServiceTest {
	Todo todoWithoutId_1, todoWithoutId_2, todoWithId_1, todoWithId_2;
	TodoRepo todoRepo;
	TodoService todoService;
	RandomService randomService;
	String id1 = "Unique id string";
	String id2 = "Unique id string2";

	@BeforeEach
	void setUpTesting() {
		todoWithoutId_1 = new Todo( "First to-do (without id)", "Description", Status.OPEN);
		todoWithoutId_2 = new Todo("Second to-do (without id)",  "Description", Status.OPEN);
		todoWithId_1 = new Todo( "Third to-do (with id)","Description", Status.OPEN);
		todoWithId_1.attachId(id1);
		todoWithId_2 = new Todo( "Fourth to-do (with id)","Description", Status.OPEN);
		todoWithId_2.attachId(id2);
		todoRepo = mock(TodoRepo.class);
		randomService = mock(RandomService.class);

		todoService = new TodoService(todoRepo, randomService);
	}


	@Order(1)
	@DisplayName("TodoService.getTodos() ...")
	@Nested
	class GetTodosTesting {

//		@Order(2)
		@Test
		@DirtiesContext
		@DisplayName("...should return a list of todos if there are todos in the database.")
		void getTodos_shouldReturnAListOfTodosIfThereAreTodosInTheDatabase() {
			//when
			when(todoRepo.getTodos()).thenReturn(List.of(todoWithId_1, todoWithId_2));
			//then assert that
			List<Todo> expected = List.of(todoWithId_1, todoWithId_2);
			List<Todo> actual = todoService.getTodos();
			assertEquals(expected, actual);
		}

//		@Order(3)
		@Test
		@DirtiesContext
		@DisplayName("...should return an empty list of todos if there are no todos in the database.")
		void getTodos_shouldReturnAnEmptyListOfTodosIfThereAreNoTodosInTheDatabase() {
			//assert that
			List<Todo> actual = todoService.getTodos();
			List<Todo> expected = new ArrayList<>();
			assertEquals(expected, actual);
		}

	}

	@Order(4)
	@DisplayName("TodoService.getTodoById() ...")
	@Nested
	class GetTodoByIdTesting {

		@Test
//		@Order(5)
		@DirtiesContext
		@DisplayName("...should return null if there is no any to-do with such id in the database.")
		void getTodoById_whenThereIsNoTodoWithSuchIdInDatabase() {
			//when
			when(todoRepo.getTodoById(id1)).thenReturn(null);
			//then assert that
			Todo expected = null;
			Todo actual = todoService.getTodoById(id1);
			assertEquals(expected, actual);
		}

		@Test
//		@Order(6)
		@DirtiesContext
		@DisplayName("...should return a to-do with the given id if there is a to-do with such id in the database.")
		void getTodoById_whenThereIsATodoWithSuchIdInDatabase() {
			//when
			when(todoRepo.getTodoById(id1)).thenReturn(todoWithId_1);
			//then assert that
			Todo actual = todoService.getTodoById(id1);
			Todo expected = todoWithId_1;
			verify(todoRepo).getTodoById(id1);
			assertEquals(expected, actual);
		}

	}


	@Order(7)
	@DisplayName("TodoService.postTodo() ...")
	@Nested
	class PostTodoTesting {

		@Test
//		@Order(8)
		@DirtiesContext
		@DisplayName("...should return a to-do with an id if the to-do is successfully added to the database.")
		void postTodo_shouldReturnATodoWithAnIdIfTheTodoIsSuccessfullyAddedToTheDatabase() {
			//when
			when(randomService.generateRandomTodoId()).thenReturn(id1);
			when(todoRepo.postTodo(todoWithoutId_1)).thenReturn(todoWithoutId_1.attachId(id1));
			//then assert that
			Todo actual = todoService.postTodo(todoWithoutId_1);
			Todo expected = todoWithoutId_1.attachId(id1);
			verify(randomService).generateRandomTodoId();
			verify(todoRepo).postTodo(todoWithoutId_1);
			assertEquals(expected, actual);
		}

	}

	@Order(9)
	@DisplayName("TodoService.updateTodo() ...")
	@Nested
	class UpdateTodoTesting {
		@Test
//		@Order(10)
		@DirtiesContext
		@DisplayName("...should return an updated to-do if the to-do is successfully updated in the database.")
		void postTodo_shouldReturnAnUpdatedTodoIfTheTodoIsSuccessfullyUpdatedInTheDatabase() {
			//when
			when(todoRepo.updateTodo(id1, todoWithId_1)).thenReturn(todoWithId_1);
			//then assert that
			Todo actual = todoService.updateTodo(id1, todoWithId_1);
			Todo expected = todoWithId_1;
			verify(todoRepo).updateTodo(id1, todoWithId_1);
			assertEquals(expected, actual);
		}

		@Test
//		@Order(11)
		@DirtiesContext
		@DisplayName("...should return null if there is no any to-do with such id in the database.")
		void postTodo_shouldReturnNullIfThereIsNoAnyTodoWithSuchIdInTheDatabase() {
			//when
			when(todoRepo.updateTodo(id1, todoWithId_1)).thenReturn(null);
			//then assert that
			Todo actual = todoService.updateTodo(id1, todoWithId_1);
			Todo expected = null;
			verify(todoRepo).updateTodo(id1, todoWithId_1);
			assertEquals(expected, actual);
		}

	}


}