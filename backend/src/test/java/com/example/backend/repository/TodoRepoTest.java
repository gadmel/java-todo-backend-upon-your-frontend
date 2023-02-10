package com.example.backend.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.example.backend.model.Status;
import com.example.backend.model.Todo;

import static org.junit.jupiter.api.Assertions.*;

@Nested
@DisplayName("TodoRepo Unit Testing")
class TodoRepoTest {
	//given
	TodoRepo todoRepo = new TodoRepo();


	@Test
	@DisplayName("TodoRepo.deleteTodo() should delete a to-do with the given id if there is a to-do with such id in the database.")
	void deleteTodo() {
		String id = "Unique id string";
		Todo todo = new Todo( "A to-do (without id)", Status.OPEN);
		todo.attachId(id);
		todoRepo.postTodo(todo);
		//when
		todoRepo.deleteTodo(id);
		//then assert that
		assertNull(todoRepo.getTodoById(id));
	}
}