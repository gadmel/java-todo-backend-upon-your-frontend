package com.example.backend.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomService {

	public String generateRandomTodoId() {
		return UUID.randomUUID().toString();
	}
}
