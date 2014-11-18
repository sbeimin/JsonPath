package com.jayway.jsonpath;

import static com.jayway.jsonpath.JsonPath.using;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class RemoveTest extends BaseTest {

    @Test
    public void fromJsonString() {
    	Map<String, Object> result = JsonPath.delete("{\"val\":1,\"val2\":2}", "$.val");
    	assertThat(result).isEqualTo(Collections.singletonMap("val2", 2));
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void fromMap() {
        Map<String, Object> model = new HashMap<String, Object>(){
			private static final long serialVersionUID = 1L;
		{
            put("a", "a-val");
            put("b", "b-val");
            put("c", "c-val");
        }};

        Configuration conf = Configuration.defaultConfiguration();

        assertThat((Map<String, Object>)using(conf).parse(model).remove("$.b"))
                .containsEntry("a", "a-val")
                .containsEntry("c", "c-val").doesNotContainKey("b");
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void fromStringToMap() {
        Configuration conf = Configuration.defaultConfiguration();

        assertThat((Map<String, Object>)using(conf).parse("{\"val\":1,\"val2\":2}").remove("$.val2"))
		        .containsEntry("val", 1).doesNotContainKey("val2");
    }
}