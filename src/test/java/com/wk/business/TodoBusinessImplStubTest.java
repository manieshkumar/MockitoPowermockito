package com.wk.business;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.wk.data.api.TodoService;
import com.wk.data.api.TodoServiceStub;

public class TodoBusinessImplStubTest {

	//Dynamic condition -> when i want to test different kind of code then it become complex to test.
	//we will not use stub in real time scenario.
	@Test
	public void testRetriveTodosRelatedToSpring_usingAStub() {
		TodoService todoServiceStub=new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl=new TodoBusinessImpl(todoServiceStub);
		List<String> filteredTodos=todoBusinessImpl.retriveTodosRelatedToSpring("Dummy");
		
		assertEquals(3, filteredTodos.size());
		assertEquals("To test the value is same or not","Learn Spring MVC",filteredTodos.get(0));
	}

}
