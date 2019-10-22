package com.millet.z_basic.net.bean;

import java.io.Serializable;

public class BaseModel<T> implements Serializable {

    private int errorCode;
    private String errorMessage;
    private T results;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public boolean isSuccess() {
        if (errorCode == 0)
            return true;
        else
            return false;
    }

}
