package com.server.Request;

public class SearchRequest {
    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SearchRequest [value=" + value + "]";
    }
    
}
