package com.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.backend.repository.TodoRepo;
import com.example.backend.model.Todo;

@Service
@RequiredArgsConstructor
public class TodoService {
	private TodoRepo todoRepo;

	public List<Todo> getTodos() {
		return todoRepo.getTodos();
	}

	public  Todo getTodoById(String id) {
		return todoRepo.getTodoById(id);
	}

	public Todo postTodo(Todo incomingTodo) {
		return todoRepo.postTodo(incomingTodo);
	}

	public Todo updateTodo(String id, Todo incomingTodo) {
		return todoRepo.updateTodo(id, incomingTodo);
	}

	public void deleteTodo(String id) {
		todoRepo.deleteTodo(id);
	}

}
