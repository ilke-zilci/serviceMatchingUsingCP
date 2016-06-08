package de.tub.ilke;

import org.junit.Test;

import de.tub.ilke.sandbox.SetMatchingProblem;

public class SetMatchingTest {

	@Test
	public void matchesBothSets(){
		SetMatchingProblem problem= new SetMatchingProblem();
		problem.execute();
	}
}
