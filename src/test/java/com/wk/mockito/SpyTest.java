package com.wk.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SpyTest {

	@Test
	public void test() {
		//List arrayListMock=mock(ArrayList.class);
		
		//with "spy(ArrayList.class)" arraylist spy it is like creating the 
		//real arrayList.we can assume like creating a new arrayList
		//spy-a spy will get all logic from the class
		//generally we go for spy when we want to use the functionality of 
		//the class and override the existing functionality
		//with the use of spy we can override the specific method
		//Spy is also called a partial mock
		//we can also verify certain invocation of a method
		
		List arrayListSpy=spy(ArrayList.class);
		assertEquals(0, arrayListSpy.size());
		
		//mocks return default value
		//stub(arrayListSpy.size()).toReturn(5);
		arrayListSpy.add("Dummy");
		//the below line will failed as it is using the actual fucntionality
		//assertEquals(5,arrayListSpy.size());//failed
		assertEquals(1, arrayListSpy.size());
		arrayListSpy.remove("Dummy");
		//assertEquals(1, arrayListSpy.size());//failed
		assertEquals(0, arrayListSpy.size());
		
		//don't use spy in real time system always go for mock
		//with spy we are using little bit of spy and little bit of mock that
		//makes it complex to maintain
		verify(arrayListSpy).add("Dummy");
		verify(arrayListSpy,never()).clear();
		
	}

}
