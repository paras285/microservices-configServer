package com.demo.propertyreader.resources;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.propertyreader.properties.ComplexProperties;
import com.demo.propertyreader.properties.ExternalProperties;
import com.demo.propertyreader.properties.SimpleProperties;

@RestController
@RequestMapping(value = "/properties")
public class AuthorResource {

	@Autowired
	private SimpleProperties simpleProperties;

	@Autowired
	private ComplexProperties complexProperties;

	@Autowired
	private ExternalProperties externalProperties;

	@GetMapping(value = "/about")
	public String readingSimpleproperties() {
		return simpleProperties.getName() + " --> " + simpleProperties.getDescription() + " running";
	}

	@GetMapping(value = "/details")
	public String readingComplexProperties() {
		return complexProperties.getAuthor() + " " + complexProperties.getAddress();
	}

	@GetMapping(value = "/skills")
	public String getSkills() {
		return Arrays.toString(externalProperties.getSkills());
	}

	@GetMapping(value = "/achievements")
	public String getAchievements() {
		return externalProperties.getAchievements().toString();
	}

}
