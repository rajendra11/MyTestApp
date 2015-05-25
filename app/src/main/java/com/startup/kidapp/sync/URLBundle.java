package com.startup.kidapp.sync;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class URLBundle {

    protected URL url;

    /**
     * HTTP Verb. Defaults to GET.
     */
    protected String verb;
    protected Map<String, String> headers = new HashMap<String, String>();
    protected String body;

    public URLBundle(URL u) {
        if (null != u) {
            url = u;
        }
    }

    public URL getUrl() {
        return url;
    }

    public String getVerb() {
        if (null != verb && !verb.isEmpty()) {
            return verb;
        } else {
            return "GET";
        }
    }

    public void setVerb( String v ) {
        if (null != v) {
            verb = v;
        } else {
            verb = "GET";
        }
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public String getBody() {
        if (null != body && !body.isEmpty()) {
            return body;
        } else {
            return "";
        }
    }

    public void setBody(String b) {
        if (null != b) {
            body = b;
        } else {
            body = "";
        }
    }
}
