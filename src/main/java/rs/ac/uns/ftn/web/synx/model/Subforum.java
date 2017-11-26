package rs.ac.uns.ftn.web.synx.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Subforum implements Serializable {

	private static final long serialVersionUID = 4793812790734696303L;
	
	private String title;
	private String description;
	private String icon;
	private List<String> rules = new ArrayList<>();
	private String leadModerator;
	private List<String> moderators = new ArrayList<>();
	
	// Lista tema
	
	public Subforum() {	}
	
	public Subforum(String title, String description, String icon, List<String> rules, String lead, List<String> moderators) {
		this.title = title;
		this.description = description;
		this.icon = icon;
		this.rules = rules;
		this.leadModerator = lead;
		this.moderators = moderators;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<String> getRules() {
		return rules;
	}

	public void setRules(List<String> rules) {
		this.rules = rules;
	}

	public String getLeadModerator() {
		return leadModerator;
	}

	public void setLeadModerator(String leadModerator) {
		this.leadModerator = leadModerator;
	}

	public List<String> getModerators() {
		return moderators;
	}

	public void setModerators(List<String> moderators) {
		this.moderators = moderators;
	}
	
}
