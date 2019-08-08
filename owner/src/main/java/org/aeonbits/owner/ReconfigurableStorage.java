package org.aeonbits.owner;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReconfigurableStorage {
    
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private final PropertiesInvocationHandler handler;
    private final Class<? extends Config> iface;
    private final String instanceClassName;

    public ReconfigurableStorage(PropertiesInvocationHandler propertiesHandler, Class<? extends Config> theInterface, Object theInstance) {
        this.handler = propertiesHandler;
        this.iface = theInterface;
        this.instanceClassName = theInstance.getClass().getName();
    }

    public Object getObject() {
        String methodName = null;
        StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        for (StackTraceElement trace : traces) {
            if (trace.getClassName() .equals(instanceClassName))
                methodName = trace.getMethodName();
        }
        
        if (methodName == null)
            throw new RuntimeException("Unexpedted: cannot find any valid mthod name for this request!");
        
        return getObject(methodName);
    }

    public Object getObject(String methodName) throws RuntimeException {
        
        Method method = null;
        Object res = null;
        try {
            method = iface.getMethod(methodName, new Class<?>[]{});
            res = handler.resolveProperty(method);
        } catch (Exception any) {
            logRequest(methodName, method, res);
            throw new RuntimeException("Cannot resolve configuration value for method "+methodName, any);
        }
        
        return res;
    }

    private void logRequest(String methodName, Method method, Object res) throws SecurityException {
        if (LOGGER.isLoggable(Level.FINE)) {
            StringBuffer sb = new StringBuffer();
            sb.append('\n');
            sb.append(String.format("\nMethodName: [%s]", methodName));
            sb.append(String.format("\nMethod: [%s]", (method == null) ? "null" : method.toGenericString()));
            sb.append(String.format("\nResult: [%s]", Objects.toString(res)));
            sb.append(String.format("\nInterface: [%s]", Objects.toString(iface)));
            for (Method m : iface.getMethods())
                sb.append(String.format("\n- [%s]", (m== null) ? "null" : m.toGenericString()));
            sb.append('\n');
   
            LOGGER.info(sb.toString());
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
