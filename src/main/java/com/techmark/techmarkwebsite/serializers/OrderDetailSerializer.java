package com.techmark.techmarkwebsite.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.techmark.techmarkwebsite.models.OrderDetail;
import com.techmark.techmarkwebsite.models.Product;

import java.io.IOException;

public class OrderDetailSerializer extends StdSerializer<OrderDetail> {
	public OrderDetailSerializer(){
		this(null);
	}
	
	public OrderDetailSerializer(Class<OrderDetail> t){
		super (t);
	}
	
	@Override
	public void serialize(OrderDetail value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		
		gen.writeStartObject();
		gen.writeObjectFieldStart("orderDetailId");
		gen.writeNumberField("orderId", value.getOrderDetailId().getOrderId());
		gen.writeNumberField("productId", value.getOrderDetailId().getProductId());
		gen.writeEndObject();
		gen.writeNumberField("productPrice",value.getProductPrice());
		gen.writeNumberField("quantity",value.getQuantity());
		gen.writeEndObject();
	}
}
