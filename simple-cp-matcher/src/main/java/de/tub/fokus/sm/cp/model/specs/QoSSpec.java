package de.tub.fokus.sm.cp.model.specs;

public interface QoSSpec {

	public enum MatchingDegree {
		SUPER, EXACT, PARTIAL, FAIL;
	}

	public void setRanking(int r);

	public int getRanking();

	public void setMatchingDegree(MatchingDegree m);

	public void applyFailRankingRule();

	public void applyExactRankingRule();

	public void applySuperRankingRule();

	public void applyPartialRankingRule();

	public void applyNoSpecRankingRule();
}
