package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Todo {
	private String description;
	private Status status;

}
