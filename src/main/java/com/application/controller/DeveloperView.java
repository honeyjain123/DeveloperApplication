package com.application.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.SessionMap;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.ui.ModelMap;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import com.application.beans.Developer;
import com.application.repository.DeveloperRepository;



@Named
@ViewScoped
public class DeveloperView implements Serializable {
//Class acting as a controller to execute all methods
	
  private static final long serialVersionUID = 1L;

  @Inject
  private DeveloperRepository developerrepo;

  private List<Developer> developers;
  
  private Developer developer = new Developer();

  @PostConstruct
  public void init() {
	// method to load all developers from database as the application runs  
	  
    developers = developerrepo.findAll(); //predefined findAll method to fetch all the developers from database using interface DeveloperRepository
  }
 
public String savedev()
//method to create a new developer and save it in database
  {
	  developerrepo.save(developer); //predefined save method to save a new developer object in database
	  developer = new Developer();
	  return "developer" + "?faces-redirect=true";
  }

public String view(Developer developer)
// method to show a particular developer details on another page and update its skills by clicking on view link
{
	Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	sessionMap.put("developer", developer);
	
	return "viewdeveloper" + "?faces-redirect=true";
}

public String update(Developer developer)
//method to update the skills by clicking on Update button
{
	Developer tempdev =  developerrepo.getOne(developer.getId());
	developer.setSkills(tempdev.getSkills() + "," + developer.getSkills());
	
	developerrepo.save(developer); // predefined method of DeveloperRepository to save the updated skills of Developer
	return "developer" + "?faces-redirect=true";
}

public List<Developer> getDevelopers() {
	return developers;
}

public void setDevelopers(List<Developer> developers) {
	this.developers = developers;
}

public Developer getDeveloper() {
	return developer;
}

public void setDeveloper(Developer developer) {
	this.developer = developer;
}
}