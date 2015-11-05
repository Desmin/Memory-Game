package gvsu.cis_350.project.core;

import gvsu.cis_350.project.utils.Util;

public enum GameSessionType {
	
	NORMAL,
	TIMED,
	LIMITED_MATCH_ATTEMPTS;
	
	public boolean isNormal() {
		return this.equals(NORMAL);
	}
	
	public boolean isTimed() {
		return this.equals(TIMED);
	}
	
	public boolean hasLimitedMatchAttempts() {
		return this.equals(LIMITED_MATCH_ATTEMPTS);
	}
	
	@Override
	public String toString() {
		return Util.toLowerFirstUC(this.name());
	}
	
}
