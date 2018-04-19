package com.emmessess.soaprequest;

/**
 * Created by umair on 4/30/2015.
 */
public interface ServiceListener<T> {

    public void success(T obj);
    public void error(ServiceError serviceError);
}
