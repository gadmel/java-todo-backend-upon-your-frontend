package com.example.backend.controller;

import com.example.backend.model.Status;
import com.example.backend.model.Todo;
import com.example.backend.repository.TodoRepo;

import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Nested
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("TodoController Integration Testing")
class TodoControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	TodoRepo todoRepo;

	Todo todo1, todo2;

	@BeforeEach
	void setUpTesting() {
		todo1 = new Todo("1",  Status.OPEN, "First to-do (without id)", "Description");
		todo2 = new Todo("2", Status.OPEN, "Second to-do (without id)", "Description");
	}


	@Order(1)
	@DisplayName("""
 		req(GET) at "api/todo"
""")
	@Nested
	class GetTodosTesting {

		@Test
		@DirtiesContext
		@DisplayName("...should return an array of to-dos if there are to-dos in the database.")
		void getTodos_shouldReturnArrayOfTodosIfThereAreTodosInTheDatabase() throws Exception {
			//given
			todoRepo.postTodo(todo1);
			todoRepo.postTodo(todo2);

			mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
					.andExpect(status().isOk())
					.andExpect(content().json("""
							[
								{
									"id": "1",
									"title": "First to-do (without id)",
									"description": "Description",
									"status": "OPEN"
								},
								{
									"id": "2",
									"title": "Second to-do (without id)",
									"description": "Description",
									"status": "OPEN"
								}
							]
							"""));
		}

		@Test
		@DirtiesContext
		@DisplayName("...should return a to-do by id if there is a to-do with the given id in the database.")
		void getTodoById_shouldReturnATodoByIdIfThereIsATodoWithTheGivenIdInTheDatabase() throws Exception {
			// given
			todoRepo.postTodo(todo1);
			// then
			mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/" + todo1.getId()))
					.andExpect(status().isOk())
					.andExpect(content().json("""
							{
								"id": "1",
								"title": "First to-do (without id)",
								"description": "Description",
								"status": "OPEN"
							}
							"""));
		}


		@Test
		@DirtiesContext
		@DisplayName("...should return an empty array if there are no to-dos in the database.")
		void getTodoById_shouldReturnAnEmptyArrayIfThereAreNoTodosInTheDatabase() throws Exception {
			//then
			mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
					.andExpect(status().isOk())
					.andExpect(content().json("[]"));
		}
	}

	@Order(2)
	@DisplayName("""
		req(POST) at "api/todo"
	""")
	@Nested
	class PostTesting {

		@Test
		@DirtiesContext
		@DisplayName("...should add a to-do to the database and return a to-do enriched with an id if the to-do is valid.")
		void getTodos_shouldAddATodoToTheDatabaseAndReturnATodoEnrichedWithAnIdIfTheTodoIsValid() throws Exception {
			//given

			//when
			mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
							.contentType(MediaType.APPLICATION_JSON)
							.content("""
{
           		"title": "First to-do (without id)",
           		"description": "Description",
           		"status": "OPEN"
           		
           }
           """
				)).andExpect(status().isOk())
					.andExpect(content().json(	"{\"title\": \"First to-do (without id)\", \"description\": \"Description\", \"status\": \"OPEN\"}"))
					.andExpect(jsonPath("$.id").isNotEmpty());
		}
	}

	@Order(3)
	@DisplayName("""
 		req(put) at "api/todo"
""")
	@Nested
	class PutTesting {
		@Test
		@DirtiesContext
		@DisplayName("...should update a to-do in the database and return the updated to-do if the to-do is valid.")
		void getTodos_shouldUpdateATodoInTheDatabaseAndReturnTheUpdatedTodoIfTheTodoIsValid() throws Exception {
			//given
			todoRepo.postTodo(todo1);
			//when
			System.out.println(todo1.getId());
			mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/" + todo1.getId())
							.contentType(MediaType.APPLICATION_JSON)
							.content("""
{
           		"title": "First to-do (without id)",
           		"description": "Description",
           		"status": "OPEN"
           		
           }
           """))
					.andExpect(status().isOk()).andDo(content -> System.out.println(content.getResponse().getContentAsString()))
					.andExpect(content().json(	"""
{
           		"title": "First to-do (without id)",
           		"description": "Description",
           		"status": "OPEN"
           		
           }
           """))
					.andExpect(jsonPath("$.id").isNotEmpty());
		}
	}

	@Order(4)
	@DisplayName("""
 		req(DELETE) at "api/todo"
""")
	@Nested
	class DeleteTesting {
		@Test
		@DirtiesContext
		@DisplayName("...should delete a to-do in the database, return response status 200, and return an empty array on a get request if the deleted to-do was the last to-do in the database.")
		void getTodos_shouldDeleteATodoInTheDatabaseAndReturnTheDeletedTodoIfTheTodoIsValid() throws Exception {
			//given
			todoRepo.postTodo(todo1);
			//when
			mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/" + todo1.getId()))
					.andExpect(status().isOk());

			mockMvc.perform(MockMvcRequestBuilders.get("/api/todo").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json("[]" ));
		}
	}

}