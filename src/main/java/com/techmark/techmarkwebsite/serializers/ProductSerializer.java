package com.techmark.techmarkwebsite.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.techmark.techmarkwebsite.models.Product;

import java.io.IOException;

public class ProductSerializer extends StdSerializer<Product> {

    public ProductSerializer(){
        this(null);
    }

    public ProductSerializer(Class<Product> t){
        super (t);
    }

    @Override
    public void serialize(Product value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();
        gen.writeNumberField("id",value.getProductId());
        gen.writeStringField("name", value.getName());
        gen.writeNumberField("price",value.getPrice());
        gen.writeStringField("description",value.getDescription());
        gen.writeStringField("imageUrl",value.getImageUrl());
        gen.writeObjectFieldStart("category");
        gen.writeNumberField("categoryId", value.getCategory().getCategoryId());
        gen.writeStringField("categoryName", value.getCategory().getName());
        gen.writeEndObject();
        gen.writeEndObject();
    }
}
