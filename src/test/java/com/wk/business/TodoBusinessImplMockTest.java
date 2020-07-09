package com.wk.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.wk.data.api.TodoService;

//@RunWith(MockitoJUnitRunner.class)

/* we cannot use two junit runner at the same time 
 * solution to the above problem is instead of using runner use @Rule introduced in 4.7
 * similar to runner , rule will run after and before the test
 * specific rule for Mockito runner is MockitoRule
 * 
 * so basically instead of using runner start using the rule
 * and now we can remove @RunWith(MockitoJunitRunner.class)
 * 
 * The Rule should be public always.
 * 
 * there can only be one runner but we can have a multiple rule.
 * */
public class TodoBusinessImplMockTest {
	
	@Rule
	public MockitoRule mockitoRule=MockitoJUnit.rule();
	

	/*
	 * @Mock-> creates automatically mock of this kind and make it available we have
	 * to use specific kind of runner with it then only mockito will be able to do
	 * that.when we use @RunWith(MockitoJunitRunner.class) to run this specific
	 * class what that allows us to do now we can remove the declaration of
	 * TodoService mock now mockito will take care of injecting it for me, now it
	 * will create and make it available for me.
	 */
	@Mock
	TodoService todoServiceMock;

	/*
	 * @InjectMocks-> it will look for specific mocks available for it or not wrt to
	 * TodoService here in our case this line of code 
	 * TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
	 * we don't require in that that were taken care by
	 *	@InjectMocks
	 *	TodoBusinessImpl todoBusinessImpl;
	 * we are actually passing the mocks object as constructor param generally
	 * we do this mockito will look inside TodoBusinessImpl the things which are
	 * declared inside is TodoService so its a member of that class,its a dependency
	 * of that class,so it will look all the dependecy of TodoBusinessImpl and
	 * checks weather any of the mocks matches it.
	 */
	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;

	//ArgumentCaptor annotation
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;
	
	
	@Test
	public void testRetriveTodosRelatedToSpring_usingAMock() {

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

		List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("Dummy");

		assertEquals(3, filteredTodos.size());
		assertEquals("To test the value is same or not", "Learn Spring MVC", filteredTodos.get(0));
	}

	@Test
	public void testRetriveTodosRelatedToSpring_usingBDD() {

		// given

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		// when
		List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("Dummy");
		// then

		assertThat(filteredTodos.size(), is(3));

	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD() {

		// given

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		// when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		// then

		then(todoServiceMock).should(atLeastOnce()).deleteTodo("Learn to dance");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring");

	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {

		// given

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		// when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		// then

		// how to see the argument passed in the delete todo?
		then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
		assertThat("here we are capturing the argument", stringArgumentCaptor.getValue(), is("Learn to dance"));

	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_mutipleArgumentCapture() {

		// given
		List<String> todos = Arrays.asList("Learn to Rock and Roll", "Learn Spring", "Learn to dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		// when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		// then

		// how to see the argument passed in the delete todo?
		then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
		assertThat("here we are capturing the argument", stringArgumentCaptor.getAllValues().size(), is(2));

	}
}
