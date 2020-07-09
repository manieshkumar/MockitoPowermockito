package com.wk.powermock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

//testing private methods
//step1
@RunWith(PowerMockRunner.class)
public class InvokingPrivateMethodTest{
	
	/*
	 * @Rule public MockitoRule mockitoRule=MockitoJUnit.rule();
	 */
	@Mock
	Dependency dependency;
	
	@InjectMocks
	SystemUnderTest systemUnderTest;
	
	@Test
	public void testRetriveTodosRelatedToSpring_usingAMock() throws Exception {


		List<Integer> stats = Arrays.asList(1,2,3);
		when(dependency.retrieveAllStats()).thenReturn(stats);

		//i cannot invoke in this particular way then how can we invoke
		//int result=systemUnderTest.privateMethodUnderTest();
		
		//solution take help of utility class of powermock
		//1st parameter object of the class
		//2nd parameter is which method do you want to call
		long result=Whitebox.invokeMethod(systemUnderTest, "privateMethodUnderTest");
	
	
		assertEquals(6, result);
		
	}
	
}
