package com.happycommunity.framework.core.util;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

/**
 * @author dannyhoo
 * @Title: StringJsonHandler
 * @Description:
 * @Created on 2018-06-11 23:34:14
 */
public class StringJsonHandler {
    private StringBuffer json = new StringBuffer("{");

    private StringJsonHandler() {
    }

    public static StringJsonHandler newInstance() {
        return new StringJsonHandler();
    }


    public StringJsonHandler appendString(String name, Object value) {
        json.append(this.getNameString(name)).append(this.getValueString(value));
        return this;
    }

    public StringJsonHandler appendObject(String name, Object value) {
        json.append(this.getNameString(name)).append(this.getValue(value));
        return this;
    }

    public StringJsonHandler appendInt(String name, int value) {
        return this.appendNonString(name, value);
    }

    public StringJsonHandler appendLong(String name, long value) {
        return this.appendNonString(name, value);
    }

    public StringJsonHandler appendDouble(String name, long value) {
        return this.appendNonString(name, value);
    }

    public StringJsonHandler appendNameValueObjects(Object... objects) {
        if (objects == null) {
            return this;
        }
        if (objects.length % 2 != 0) {
            return this;
        }

        int i = 0;
        for (Object object : objects) {
            if (i % 2 == 0) {
                json.append(this.getNameString((String) object));
            } else {
                json.append(this.getValue(object));
            }
            i++;
        }
        return this;
    }

    public String getJson() {
        if (json.toString().endsWith(this.getTwoFieldSeparator())) {
            json.delete(json.length() - this.getTwoFieldSeparator().length(), json.length());
        }
        json.append("}");
        return json.toString();
    }

    public StringJsonHandler appendNonString(String name, Object value) {
        json.append(this.getNameString(name)).append(this.getValueNonString(value));
        return this;
    }

    private String getNameString(String name) {
        StringBuffer nameSb = new StringBuffer();
        nameSb.append(this.getStringSymbol())
                .append(name)
                .append(this.getStringSymbol())
                .append(this.getNameValueSeparetor());
        return nameSb.toString();
    }

    private String getValueString(Object value) {
        StringBuffer valueSb = new StringBuffer();
        valueSb.append(this.getStringSymbol())
                .append(value)
                .append(this.getStringSymbol())
                .append(this.getTwoFieldSeparator());
        return valueSb.toString();
    }

    private String getValueNonString(Object value) {
        StringBuffer valueSb = new StringBuffer();
        valueSb.append(value)
                .append(this.getTwoFieldSeparator());
        return valueSb.toString();
    }


    private String getValue(Object value) {
        if (value instanceof Long ||
                value instanceof Integer ||
                value instanceof Float ||
                value instanceof Double ||
                value instanceof BigDecimal) {
            return this.getValueNonString(value);
        }
        if (value instanceof String) {
            return this.getValueString(value);
        }
        return this.getValueNonString(this.toJson(value));
    }

    private String getStringSymbol() {
        return "\"";
    }

    private String getNameValueSeparetor() {
        return ":";
    }

    private String getTwoFieldSeparator() {
        return ",";
    }

    private String toJson(Object object) {
        try {
            return JSON.toJSONString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return object.toString();
        }
    }
}

