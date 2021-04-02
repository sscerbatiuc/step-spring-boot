package edu.step.employeemanager.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import edu.step.employeemanager.model.Company;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

@JsonComponent
public class CompanyJsonDeserializer extends JsonDeserializer<Company> {
    @Override
    public Company deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Company company = new Company();
        final ObjectCodec codec = jsonParser.getCodec();
        final JsonNode employeeJson = codec.readTree(jsonParser);
        if (employeeJson.get("id") != null) {
            company.setId(employeeJson.get("id").asInt());

        }
        if (employeeJson.get("name") != null) {
            final String name = employeeJson.get("name").asText();
            company.setName(name);

        }
        company.setEmployees(new HashSet<>());
        return company;
    }
}
