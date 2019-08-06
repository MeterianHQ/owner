package org.aeonbits.owner.reconfigure;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Set;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Reconfigurable;

@Sources({"file:~/app.properties"})
public interface AppConfig extends Config, Reconfigurable {

    @DefaultValue("www")
    @Key("env")
    public String getSimpleString();
    
    @DefaultValue("http://${env}.meterian.com/foobar")
    @Key("uri")
    public URI getUriWithExpansion();

    @DefaultValue("12345")
    @Key("ival")
    public int getSimpleInteger();

    @DefaultValue("/tmp")
    @Key("file")
    public File getSimpleFile();

    @DefaultValue("true")
    @Key("flag")
    public Boolean getSimpleBoolean();

    @DefaultValue("1234567890123456789")
    @Key("lval")
    public long getSimpleLong();
    
    @DefaultValue("alfa,beta,gamma")
    @Key("string.array")
    public String[] getStringArray();
    
    @DefaultValue("alfa")
    @Key("string.set")
    public Set<String> getStringSet();
    
    @DefaultValue("alfa,beta,gamma")
    @Key("string.list")
    public List<String> getStringList();

    @DefaultValue("http://www.meterian.com")
    @Key("url")
    public URL getURL();

    @DefaultValue("12.25")
    @Key("dval")
    public double getSimpleDouble();
    
}
