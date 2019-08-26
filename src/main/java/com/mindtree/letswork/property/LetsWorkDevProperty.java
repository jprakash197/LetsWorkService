package com.mindtree.letswork.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author M1053435
 *
 */
@Component
@PropertySource("classpath:app.dev.properties")
@ConfigurationProperties(prefix = "app.dev") 
public class LetsWorkDevProperty {
	
	private String title; 
	private String project; 
	private String version; 
	private String environment;
	
	
	public String getTitle() {
		return title;
	}
	public String getProject() {
		return project;
	}
	public String getVersion() {
		return version;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	


}
