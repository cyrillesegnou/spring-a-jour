package fr.gtm.bovoyages.utils;

import lombok.Data;

@Data

public class InscriptionResponse {
	String explanation;
	boolean state;
	
	public InscriptionResponse(String explanation, boolean state) {
		this.explanation = explanation;
		this.state = state;
		
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
}
