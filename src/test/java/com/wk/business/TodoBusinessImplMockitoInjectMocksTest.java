package com.wk.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.wk.data.api.TodoService;

public class TodoBusinessImplMockitoInjectMocksTest {

	// Dynamic condition -> when i want to test different kind of code then it
	// become complex to test.
	// we will not use stub in real time scenario.
	@Test
	public void testRetriveTodosRelatedToSpring_usingAMock() {
		TodoService todoServiceMock = mock(TodoService.class);

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("Dummy");

		assertEquals(3, filteredTodos.size());
		assertEquals("To test the value is same or not", "Learn Spring MVC", filteredTodos.get(0));
	}

	// with when we use thenRetun
	// with given we use willReturn

	// when->thenReturn
	// given->thenReturn

	@Test
	public void testRetriveTodosRelatedToSpring_usingBDD() {

		// given
		TodoService todoServiceMock = mock(TodoService.class);

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

		// when
		List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("Dummy");
		// then

		assertThat(filteredTodos.size(), is(3));

	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD() {

		// given
		TodoService todoServiceMock = mock(TodoService.class);

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

		// when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		// then

		// verify()-this method in mockito would enable you to check that some method is
		// called
		// here inside verify(we have to pass on which mock i am calling the
		// method).methodName(value)

		// times method are as follows
		// ->times(1)
		// ->atLeast(minNumberOfInvocations)
		// ->atLeastOnce()
		// verify(todoServiceMock,times(1)).deleteTodo("Learn to dance");
		// verify(todoServiceMock).deleteTodo("Learn to dance");
		// this will check that on that method Learn Spring Mvc never got deleted
		// because it is relate to Spring
		// here i am checking this specific thing is never called
		// verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
		// now my requirement is i want to know how many "times" these specific method
		// is
		// called
		// lets suppose i want to called it for only one time

		// alternate way to call is we can also do in this way as we are using then
		// then is very much similar to verify
		then(todoServiceMock).should(atLeastOnce()).deleteTodo("Learn to dance");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring");

	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {

		//how to see the argument passed in the delete todo?
		ArgumentCaptor<String> stringArgumentCaptor=ArgumentCaptor.forClass(String.class);
		
		//1st step declare an argument captor
		//define argument captor on specific method call
		//once capture then check it.
		
		
		// given
		TodoService todoServiceMock = mock(TodoService.class);

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

		// when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		// then

		//how to see the argument passed in the delete todo?
		then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
		assertThat("here we are capturing the argument",stringArgumentCaptor.getValue(), is("Learn to dance"));
		
	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_mutipleArgumentCapture() {

		//how to see the argument passed in the delete todo?
		ArgumentCaptor<String> stringArgumentCaptor=ArgumentCaptor.forClass(String.class);
		
		//1st step declare an argument captor
		//define argument captor on specific method call
		//once capture then check it.
		
		
		// given
		TodoService todoServiceMock = mock(TodoService.class);

		List<String> todos = Arrays.asList("Learn to Rock and Roll", "Learn Spring", "Learn to dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

		// when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		// then

		//how to see the argument passed in the delete todo?
		then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
		assertThat("here we are capturing the argument",stringArgumentCaptor.getAllValues().size(), is(2));
		
	}

}
