package com.wk.data.api;

import java.util.Arrays;
import java.util.List;

public class TodoServiceStub implements TodoService {

	public void deleteTodo(String todo) {

	}

	public List<String> retrieveTodos(String user) {
		return Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
	}

}
