package org.jvalue.ods.coreservice.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.TimeZone;

import static org.junit.Assert.*;

public class PipelineMetadataTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testDeserialization() throws IOException {
        String metadataJson = "{" +
                "\"author\":\"harri\"," +
                "\"license\":\"nobodymayuseanything v1.0\"" +
                "}";

        PipelineMetadata result = mapper.readValue(metadataJson, PipelineMetadata.class);

        assertEquals("harri", result.getAuthor());
        assertEquals("nobodymayuseanything v1.0", result.getLicense());
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTime(result.getCreationTimestamp());
        assertEquals(LocalDateTime.now().getYear(), cal.get(Calendar.YEAR));
        assertEquals(LocalDateTime.now().getDayOfYear(), cal.get(Calendar.DAY_OF_YEAR));
    }
}
