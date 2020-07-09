package com.wk.mockito;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HamcrestMatchersTest {

	@Test
	public void test() {
		List<Integer> scores=Arrays.asList(99,100,101,105);
		//scores has four items
		assertThat(scores,hasSize(4));
		assertThat(scores,hasItems(99,100));
		
		//every item on that list has some specific condition >90
		assertThat(scores,everyItem(greaterThan(90)));
		assertThat(scores,everyItem(lessThan(190)));
	
		
		//String related stuff
		//is empty String
		assertThat("",isEmptyString());
		//is empty or null String
		assertThat(null,isEmptyOrNullString());
		
		//Arrays
		Integer[] marks={1,2,3};
		
		assertThat(marks, arrayWithSize(3));
		
		//a specific array contain exact values
		assertThat(marks, arrayContaining(1,2,3));
		//i don't want to worry about the order
		assertThat(marks, arrayContainingInAnyOrder(2,1,3));
	}
	

}
