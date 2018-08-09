package com.techmark.techmarkwebsite.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Used to serialize Java.util.Date, which is not a common JSON
 * type, so we have to create a custom serialize method;.
 */

/** Vladi - Json serializer manages the visualized output of a http request (get, put, post) in a json format. Especially helpful when there are embedded jsons within a json and when there are mapped columns (classes) that lead to embedding a list/ lists of json(s) within the main json => you can prevent such visualization of embedded jsons with the help of the json serializer. You can also format embedded json as a series of normal variables within the main json.*/

@Component
public class JsonDateSerializer extends JsonSerializer<Date> {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
			throws IOException {
		String formattedDate = dateFormat.format(date);
		gen.writeString(formattedDate);
	}
}
