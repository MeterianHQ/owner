package org.aeonbits.owner.reconfigure;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.aeonbits.owner.ConfigFactory;
import org.junit.Test;

public class ReconfigurableTest {

    @Test
    public void shouldLoadSimpleString() {
        assertEquals("www", config().getSimpleString());
    }

    @Test
    public void shouldLoadSimpleStringWhenOverriddenViaProperties() {
        assertEquals("qa", config("env", "qa").getSimpleString());
    }

    @Test
    public void shouldLoadExpandedURI() throws Exception {
        assertEquals(new URI("http://www.meterian.com/foobar"), config().getUriWithExpansion());
    }
    
    @Test
    public void shouldLoadSimpleInteger() throws Exception {
        assertEquals(12345, config().getSimpleInteger());
    }
    
    @Test
    public void shouldLoadSimpleFile() throws Exception {
        assertEquals(new File("/tmp"), config().getSimpleFile());
    }
    
    @Test
    public void shouldLoadSimpleBoolean() throws Exception {
        assertEquals(Boolean.TRUE, config().getSimpleBoolean());
    }

    @Test
    public void shouldLoadSimpleLong() throws Exception {
        assertEquals(1234567890123456789L, config().getSimpleLong());
    }

    @Test
    public void shouldLoadStringArray() throws Exception {
        assertArrayEquals(new String[]{"alfa","beta","gamma"}, config().getStringArray());
    }

    @Test
    public void shouldLoadStringSet() throws Exception {
        assertEquals(asSet("alfa"), config().getStringSet());
    }

    @Test
    public void shouldLoadStringList() throws Exception {
        assertEquals(Arrays.asList("alfa","beta","gamma"), config().getStringList());
    }

    @Test
    public void shouldLoadURL() throws Exception {
        assertEquals(new URL("http://www.meterian.com"), config().getURL());
    }

    @Test
    public void shouldLoadSimpleDouble() throws Exception {
        assertEquals(12.25, config().getSimpleDouble(), 0.0);
    }
    
    private AppConfig config(String... params) {
        Properties properties = new Properties();
        for (int i=0; i<params.length; i+=2)
            properties.put(params[i], params[i+1]);
        
        AppConfig config = ConfigFactory.reconfigure(AppConfig.class, new AppConfigImpl(), properties );
        return config;
    }
    
    private <T> Set<T> asSet(T... values) {
        return new HashSet<T>(Arrays.asList(values));
    }

}
