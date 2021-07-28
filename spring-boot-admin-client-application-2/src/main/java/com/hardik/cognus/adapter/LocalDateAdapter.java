package com.hardik.cognus.adapter;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate>{

	@Override
	public LocalDate unmarshal(String textualDate) throws Exception {
		return LocalDate.parse(textualDate);
	}

	@Override
	public String marshal(LocalDate localDate) throws Exception {
		return localDate.toString();
	}

}
