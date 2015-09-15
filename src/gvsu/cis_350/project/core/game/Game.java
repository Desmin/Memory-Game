package gvsu.cis_350.project.core.game;

import gvsu.cis_350.project.core.Node;

public interface Game extends Node {
	
	public boolean initialize();
	
	public void reset();
	
	public boolean update();
	
	public boolean shutdown();

}
