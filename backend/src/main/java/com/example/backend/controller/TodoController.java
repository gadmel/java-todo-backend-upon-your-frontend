package com.example.backend.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

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

}
