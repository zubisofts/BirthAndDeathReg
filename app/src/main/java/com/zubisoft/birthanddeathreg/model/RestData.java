package com.zubisoft.birthanddeathreg.model;

public class RestData {
    private boolean hasError;
    private Object data;

    public RestData(boolean hasError, Object data) {
        this.hasError = hasError;
        this.data = data;
    }

    public boolean hasError() {
        return hasError;
    }

    public void setError(boolean hasError) {
        this.hasError = hasError;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
