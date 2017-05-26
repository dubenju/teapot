package org.teapot.db.orm;

public interface IKeyValue {
    public Object getValueByKey(String key);
    public void   setValueByKey(String key, Object value);
}
