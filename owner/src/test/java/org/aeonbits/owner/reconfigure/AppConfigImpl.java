package org.aeonbits.owner.reconfigure;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Set;

import org.aeonbits.owner.Reconfigurable;
import org.aeonbits.owner.ReconfigurableStorage;

public class AppConfigImpl implements AppConfig, Reconfigurable {
   
    private ReconfigurableStorage storage;

    @Override
    public void reconfigure(ReconfigurableStorage storage) {
        this.storage = storage;
    }

    @Override
    public String getSimpleString() {
        return storage.getString();
    }

    @Override
    public URI getUriWithExpansion() {
        return storage.getURI();
    }

    @Override
    public int getSimpleInteger() {
        return storage.getInteger();
    }

    @Override
    public File getSimpleFile() {
        return storage.getFile();
    }

    @Override
    public Boolean getSimpleBoolean() {
        return storage.getBoolean();
    }

    @Override
    public long getSimpleLong() {
        return storage.getLong();
    }

    @Override
    public String[] getStringArray() {
        return storage.getArray(String.class);
    }

    @Override
    public Set<String> getStringSet() {
        return storage.getSet(String.class);
    }

    @Override
    public List<String> getStringList() {
        return storage.getList(String.class);
    }

    @Override
    public URL getURL() {
        return storage.getURL();
    }

    @Override
    public double getSimpleDouble() {
        return storage.getDouble();
    }

}
