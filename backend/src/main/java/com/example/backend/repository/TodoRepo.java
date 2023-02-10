package com.example.backend.repository;

import com.example.backend.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepo {

	private List<Todo> todos;

	public List<Todo> getTodos() {
		return todos;
	}

	public Todo getTodoById(String id) {
		return todos.stream()
				.filter(todo -> todo.getId().equals(id))
				.findFirst()
				.orElse(null);
	}

	public Todo postTodo(Todo incomingTodo) {
		todos.add(incomingTodo);
		return incomingTodo;
	}

	public Todo updateTodo(String id, Todo incomingTodo) {
		todos.stream()
				.filter(todo -> todo.getId().equals(id))
				.findFirst()
				.ifPresent(todo -> {
					todo.setDescription(incomingTodo.getDescription());
					todo.setStatus(incomingTodo.getStatus());
				});
		return incomingTodo;
	}

	public void deleteTodo(String id) {
		todos.removeIf(todo -> todo.getId().equals(id));
	}
}
