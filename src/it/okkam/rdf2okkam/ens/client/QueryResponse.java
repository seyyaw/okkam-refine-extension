package it.okkam.rdf2okkam.ens.client;

import java.util.List;

public class QueryResponse {
	
	private String id; // entity identifier (ookamid)
	private String name;
	private List type;
	private double score;
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public boolean isMatch() {
		return match;
	}
	public void setMatch(boolean match) {
		this.match = match;
	}
	private boolean match;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ResponseType> getType() {
		return type;
	}
	public void setType(List<ResponseType> type) {
		this.type = type;
	}
	

}
