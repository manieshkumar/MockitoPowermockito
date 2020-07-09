package com.wk.powermock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

//testing Constructor


//note writing mocks for constructor,private method,static method is considered to bad practice
//in real time
//do this only in legacy project which is badly designed

@RunWith(PowerMockRunner.class)

//step1
@PrepareForTest(SystemUnderTest.class)
public class MockingConstructorTest{
	
	/*//not required
	 * @Mock Dependency dependency;
	 */	
	@InjectMocks
	SystemUnderTest systemUnderTest;
	
	@Mock
	ArrayList mockList;
	
	@Test
	public void testBadName() throws Exception {
		
		//step-1=>PrepareForTest=> pass the class name which is using ArrayList constructor 
		//here in our case it is SystemUnderTest
		//In static method case we need to pass the class which contains the static method
		//but in case of constructor give the class name which is making use of the 
		//constructor don't use the name of constructor as class
		
		//steps-2=>use a specific method to override the constructor.

		List<Integer> stats = Arrays.asList(1,2,3);
		when(mockList.size()).thenReturn(5);
		//steps-2
		//now how to override the constructor of a arraylist class 
		
		PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);
		int size = systemUnderTest.methodUsingAnArrayListConstructor();
		
	
		assertEquals(5, size);
		
	}
	
}
