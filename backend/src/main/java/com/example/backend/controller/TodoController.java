package com.example.backend.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Todo;
import com.example.backend.service.TodoService;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

	private TodoService todoService;

	@PostMapping("")
	public Todo postTodo(@RequestBody Todo incomingTodo) {
		return todoService.postTodo(incomingTodo);
	}

}
