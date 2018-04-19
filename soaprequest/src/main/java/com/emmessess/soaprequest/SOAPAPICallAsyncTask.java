package com.emmessess.soaprequest;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Sadiq on 03/07/18.
 */

public class SOAPAPICallAsyncTask extends AsyncTask<Void, Void, SoapObject> {
    Context context;
    String baseURL;
    String method;
    String nameSpace;
    String action;
    PropertyInfo [] propertyInfo;
    private ServiceListener mListener;

    public SOAPAPICallAsyncTask(Context context , String baseURL, String method, String nameSpace, String action,
                                PropertyInfo[] propertyInfo, ServiceListener mListener) {
        this.context = context;
        this.baseURL = baseURL+method;
        this.method = method;
        this.nameSpace = nameSpace;
        this.action = action;
        this.propertyInfo = propertyInfo;
        this.mListener = mListener;
    }

    public SOAPAPICallAsyncTask(String baseURL, String method, String nameSpace, String action,
                                PropertyInfo[] propertyInfo, ServiceListener mListener) {
        this.baseURL = baseURL+method;
        this.method = method;
        this.nameSpace = nameSpace;
        this.action = action;
        this.propertyInfo = propertyInfo;
        this.mListener = mListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }



    @Override
    protected SoapObject doInBackground(Void... voids) {
        SoapObject request = new SoapObject(nameSpace, method);
        if (propertyInfo != null){

            for (int i = 0 ; i < propertyInfo.length ; i++){
                request.addProperty(propertyInfo[i]);
            }
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(baseURL);
        try {
            httpTransport.call(action, envelope);
            SoapObject result = (SoapObject) envelope.bodyIn;
            if (BuildConfig.DEBUG) {
                Log.e("Params Sent", "Response=" + envelope.bodyOut);
                Log.e("Response", "Response=" + result.toString());
            }
            return result;
        } catch (XmlPullParserException e) {
            mListener.error(new ServiceError(NetworkUtils.COMMUNICATION_ERROR));
            e.printStackTrace();
        } catch (IOException e) {
            if (NetworkUtils.isInternetAvailable(context))
                mListener.error(new ServiceError(e.getLocalizedMessage()));
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(SoapObject soapObject) {
        super.onPostExecute(soapObject);
        if (soapObject!=null){
            mListener.success(soapObject);
        }else{
            if (context!=null) {
                if (NetworkUtils.isInternetAvailable(context)) {
                    mListener.error(new ServiceError(NetworkUtils.COMMUNICATION_ERROR));
                } else {


                    mListener.error(new ServiceError(NetworkUtils.CONNECTION_ERROR));

                }
            }
        }
    }
}
