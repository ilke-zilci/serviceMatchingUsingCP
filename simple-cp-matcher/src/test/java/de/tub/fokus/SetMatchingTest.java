package de.tub.fokus;

import org.junit.Test;

import de.tub.fokus.sandbox.SetMatchingProblem;

public class SetMatchingTest {

	@Test
	public void matchesBothSets(){
		SetMatchingProblem problem= new SetMatchingProblem();
		problem.execute();
	}
}
