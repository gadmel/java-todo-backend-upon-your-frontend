package com.example.backend.model;

import lombok.*;

@Getter
@Setter
@ToString
public class Todo {
	private String id;
	private String description;
	private Status status;

	public Todo(String description, Status status) {
		this.description = description;
		this.status = status;
	}

	public Todo attachId(String incomingId) {
		this.setId(incomingId);
		return this;
	}

}
