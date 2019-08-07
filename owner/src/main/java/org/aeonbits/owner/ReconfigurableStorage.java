package org.aeonbits.owner;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Set;

public class ReconfigurableStorage {
    
    private PropertiesInvocationHandler handler;
    private Class<? extends Config> iface;

    public ReconfigurableStorage(PropertiesInvocationHandler propertiesHandler, Class<? extends Config> intface) {
        this.handler = propertiesHandler;
        this.iface = intface;
    }

    public Object getObject() {
        String methodName = Thread.currentThread() 
            .getStackTrace()[3] 
            .getMethodName(); 
    
        return getObject(methodName);
    }

    public Object getObject(String methodName) throws RuntimeException {
        try {
            Method method = iface.getMethod(methodName, new Class<?>[]{});
            Object res = handler.resolveProperty(method);
            return res;
        } catch (Exception any) {
            throw new RuntimeException("Cannot resolve configuration value for method "+methodName);
        }
    }

    public String getString() {
        return safeCast(getObject(), String.class);
    }

    public URI getURI() {
        return safeCast(getObject(), URI.class);
    }

    public Integer getInteger() {
        return safeCast(getObject(), Integer.class);
    }

    public File getFile() {
        return safeCast(getObject(), File.class);
    }

    public Boolean getBoolean() {
        return safeCast(getObject(), Boolean.class);
    }

    public Long getLong() {
        return safeCast(getObject(), Long.class);
    }

    public String[] getStringArray() {
        return safeCast(getObject(), String[].class);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] getArray(Class<T> clazz) {
        return (T[]) getObject();
    }

    @SuppressWarnings("unchecked")
    public <T> Set<T> getSet(Class<T> clazz) {
        return (Set<T>)getObject();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getList(Class<T> clazz) {
        return (List<T>)getObject();
    }

    public URL getURL() {
        return safeCast(getObject(), URL.class);
    }

    public Double getDouble() {
        return safeCast(getObject(), Double.class);
    }

    @SuppressWarnings("unchecked")
    public <T> T safeCast(Object res, Class<T> clazz) {
        try {
            return (T) res;
        } catch (ClassCastException ex) {
            throw new RuntimeException("Cannot resolve configuration value to class "+clazz+" - real class: "+res.getClass());
        }
    }

}
