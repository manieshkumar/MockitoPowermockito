package com.wk.business;

import java.util.ArrayList;
import java.util.List;

import com.wk.data.api.TodoService;

//TodoBusinessImpl is SUT(System under test)
//TodoService is Dependency and its implementation is not available in this example we will see how to mock the dependency
//HOW we can mock the TodoBusinessImpl when we do not have the real implementation of TodoService available

public class TodoBusinessImpl {

	private TodoService todoService;

	public TodoBusinessImpl(TodoService todoService) {
		this.todoService = todoService;
	}

	public List<String> retriveTodosRelatedToSpring(String user) {

		List<String> filteredTodos = new ArrayList<String>();
		List<String> todos = todoService.retrieveTodos(user);

		for (String todo : todos) {
			filteredTodos.add(todo);
		}

		return filteredTodos;
	}

	public void deleteTodosNotRelatedToSpring(String user) {

		List<String> todos = todoService.retrieveTodos(user);

		for (String todo : todos) {
			if (!todo.contains("Spring")) {
				todoService.deleteTodo(todo);

			}
		}

	}
}