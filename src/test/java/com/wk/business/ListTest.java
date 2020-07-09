package com.wk.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void letsMockSizeMethod() {
		List listMock=mock(List.class);
		when(listMock.size()).thenReturn(3);
		assertEquals(3,listMock.size());
		assertEquals(3,listMock.size());
		assertEquals(3,listMock.size());
	}

	//here i want to call with multiple value
	@Test
	public void letsMockSize_ReturnMultipleValues() {
		List listMock=mock(List.class);
		when(listMock.size()).thenReturn(3).thenReturn(2).thenReturn(3);
		assertEquals(3,listMock.size());
		assertEquals(2,listMock.size());
		assertEquals(3,listMock.size());
	}
	
	
	//lets say every-time listMock.get() is called then i would want to always "return in mockito" irrespective of
	//parameter is being passed 
	//then in  that scenario argument matchers is being passed.
	
	@Test
	public void letsMockListGet() {
		List listMock=mock(List.class);
		//Argument Matcher
		//anyInt() is argument matcher the matcher does not do any type check it is only there to avoid casting.
		when(listMock.get(anyInt())).thenReturn("in mockito");
		assertEquals("in mockito",listMock.get(0));
		//assertEquals(null, listMock.get(1));
		assertEquals("in mockito",listMock.get(2));
		
	}
	@Test(expected = ArithmeticException.class)
	public void letsMockList_throwAnException() {
		List listMock=mock(List.class);
		//Argument Matcher
		when(listMock.get(anyInt())).thenThrow(new ArithmeticException("SOMETHING"));
		listMock.get(0);
		
	}
	
	@Test(expected = RuntimeException.class)
	public void letsMockList_throwAnExceptionRuntime() {
		List listMock=mock(List.class);
		//Argument Matcher
		when(listMock.get(anyInt())).thenThrow(new Throwable("SOMETHING"));
		listMock.get(0);
		
	}

	
	@Test(expected = RuntimeException.class)
	//mockito does not allow combination of matcher and hard coded values
	
	public void letsMockList_MixingUp() {
		List listMock=mock(List.class);
		//Argument Matcher
		//when(listMock.get(anyInt(),5)).thenThrow(new RuntimeException("SOMETHING"));
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("SOMETHING"));
		listMock.get(0);
		
	}
	
	//BDD-> behaviour driven development
	//follow this structure for development
	//Given-setup
	//when-actual method is called
	//then- asserts steps
	
	@Test
	public void letsMockList_usingBDD() {

		//given
		List<String> listMock=mock(List.class);
		given(listMock.get(anyInt())).willReturn("in mockito");
		
		//when
		String firstElement=listMock.get(0);
		
		//then 
		assertThat(firstElement,is("in mockito"));
	}	
}
