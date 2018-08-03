package com.techmark.techmarkwebsite.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.techmark.techmarkwebsite.models.Order;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class OrderSerializer extends StdSerializer<Order> {
	
	public OrderSerializer(){
		this(null);
	}
	
	public OrderSerializer(Class<Order> t){
		super (t);
	}
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public void serialize(Order value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		
		String formattedDate = dateFormat.format(value.getDate());
		
		gen.writeStartObject();
		gen.writeNumberField("orderId",value.getOrderId());
		gen.writeNumberField("userId", value.getUser().getUserId());
		gen.writeStringField("date", formattedDate);
		gen.writeEndObject();
	}
}
