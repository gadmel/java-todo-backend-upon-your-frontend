package com.example.backend.model;

import lombok.*;

@Data
public class Todo {
	private String id;
	private String title;
	private String description;
	private Status status;
	//next lin is necessary for Spring to be able to deserialize the JSON
	public Todo() {
	}
	public Todo(String title, String description, Status status) {
		this.description = description;
		this.title = title;
		this.status = status;
	}
	public Todo(String id, Status status , String title, String description) {
		this.title = title;
		this.id = id;
		this.description = description;
		this.status = status;
	}

	public Todo attachId(String incomingId) {
		this.setId(incomingId);
		return this;
	}

}
