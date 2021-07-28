package com.hardik.cognus.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.hardik.cognus.adapter.LocalDateAdapter;

import lombok.Data;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class UserXmlDto implements Serializable {

	private static final long serialVersionUID = 3612694084383208257L;

	@XmlAttribute(name = "id", required = true)
	private String userId;

	@XmlAttribute(name = "email_id", required = true)
	private String emailId;

	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	@XmlAttribute(name = "date_of_birth", required = false)
	private LocalDate dateOfBirth;

	@XmlValue
	private String name;

}
