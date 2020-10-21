package com.demo.propertyreader.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.demo.propertyreader.models.Address;
import com.demo.propertyreader.models.Author;

@ConfigurationProperties(prefix = "app")
@Component
public class ComplexProperties {

	private Author author;
	private List<Address> address;
	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "ComplexProperties [author=" + author + ", address=" + address + "]";
	}

}
