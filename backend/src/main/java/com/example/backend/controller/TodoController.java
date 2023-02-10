package com.example.backend.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.backend.model.Todo;
import com.example.backend.service.TodoService;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

	private TodoService todoService;

	@GetMapping("")
	public List<Todo> getTodos() {
		return todoService.getTodos();
	}

	@GetMapping("/{id}")
	public Todo getTodoById(@PathVariable String id) {
		return todoService.getTodoById(id);
	}

	@PostMapping("")
	public Todo postTodo(@RequestBody Todo incomingTodo) {
		return todoService.postTodo(incomingTodo);
	}

	@PutMapping("/{id}")
	public Todo updateTodo(@PathVariable String id, @RequestBody Todo incomingTodo) {
		return todoService.updateTodo(id, incomingTodo);
	}

	@DeleteMapping("/{id}")
	public void deleteTodo(@PathVariable String id) {
		todoService.deleteTodo(id);
	}

}
