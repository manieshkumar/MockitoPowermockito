package com.wk.powermock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

//step1
@RunWith(PowerMockRunner.class)
//step2.1->here we are telling again we are going to mock the static class
@PrepareForTest(UtilityClass.class)
public class MockingStaticMethodTest{
	
	/*
	 * @Rule public MockitoRule mockitoRule=MockitoJUnit.rule();
	 */
	@Mock
	Dependency dependency;
	
	@InjectMocks
	SystemUnderTest systemUnderTest;
	
	@Test
	public void testRetriveTodosRelatedToSpring_usingAMock() {
//		TodoService todoServiceMock = mock(TodoService.class);

		List<Integer> stats = Arrays.asList(1,2,3);
		when(dependency.retrieveAllStats()).thenReturn(stats);

		//step2
		PowerMockito.mockStatic(UtilityClass.class);
		
		//this should throw an exception as we are not mocking it yet
		//now i want to mock utilitymethod.static method now this is the regular way we use 
		//this will also throw exception because this is still not being mocked out
		when(UtilityClass.staticMethod(6)).thenReturn(150);
		
		
		int result=systemUnderTest.methodCallingAStaticMethod();
		
	
		//step-1->solution to above problem is use a specific runner class
		//step-2->Initialize few things like which class is having a static method that is Utility class which i want to mock them
		//step-3->then mock the things for like static methods
		
		//now i want to check the value if what i am returning is coming or not
	
		assertEquals(150, result);
		
		//now i really want to check Utilityclass.staticmethod() is really called
		PowerMockito.verifyStatic();
		UtilityClass.staticMethod(6);
		//UtilityClass.staticMethod(5);
	}
	
}











