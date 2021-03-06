package co.opentune.android.api;
import android.os.SystemClock;
import android.util.Log;

import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import co.opentune.android.Utility.Network.NetworkUtility;
import co.opentune.android.singleton.OpenTuneApplication;


public class Api {

    public static final int HTTP_GET = 1, HTTP_POST = 3;
    public static final int MAX_TRIES = 5;
    public static OpenTuneApplication openTuneApplication;

    public static void init (OpenTuneApplication openTuneApplication){
        Api.openTuneApplication = openTuneApplication;
    }

    public static DefaultHttpClient getHttpClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", sf, 443));

            ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);

            return new DefaultHttpClient(cm, params);

        } catch (Exception ex) {

            ex.printStackTrace();

            HttpParams params = new BasicHttpParams();
            ConnManagerParams.setMaxTotalConnections(params, 1);
//		    ConnPerRoute cpr = new ConnPerRoute() {
//		        @Override
//		        public int getMaxForRoute(HttpRoute httpRoute) {
//		            return 5;
//		        }
//		    };
//		    ConnManagerParams.setMaxConnectionsPerRoute(params, cpr);
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

            ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);

            return new DefaultHttpClient(cm, params);
        }
    }

    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
        //return "1365599329";
    }

    public static JSONObject call(String url, TreeMap<String, String> params, int httpMethod) {
        if (NetworkUtility.hasConnection(openTuneApplication)) {
            int tries = 0;
            while (tries < MAX_TRIES) {
                try {
                    JSONObject json = privateCall(url, params, httpMethod);
                    Log.d("see Json", json.toString(1));
                    if (json != null) {
                        return json;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();

                    tries++;
                    if (tries < MAX_TRIES) SystemClock.sleep(500);
                }
            }
            return null;
        }
        return null;
    }


    private static JSONObject privateCall(String url, TreeMap<String, String> params, int httpMethod) throws Exception {
        String sRequest = url;
        HttpClient client = getHttpClient();
        HttpRequestBase httpReq;
        if (httpMethod == HTTP_GET) {
            if (params != null && params.size() > 0) {
                sRequest += "&" + getParamsString(params);
            }
            httpReq = new HttpGet(sRequest);
        } else {
            httpReq = new HttpPost(sRequest);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (params != null) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    nameValuePairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
                try {
                    ((HttpPost) httpReq).setEntity(new UrlEncodedFormEntity(nameValuePairs));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        Log.d("Link", sRequest);
        ResponseHandler<String> handler = new BasicResponseHandler();
        String result = client.execute(httpReq, handler);
        //we assumpt all results are json
        return new JSONObject(new String(result.getBytes(), "UTF-8"));
    }


    public static String getParamsString(TreeMap<String, String> params) {
        if (params != null && params.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> param : params.entrySet()) {
                if (sb.length() > 0) sb.append("&");
                sb.append(param.getKey() + "=" + param.getValue());
            }
            return sb.toString();
        } else {
            return "";
        }
    }
}
