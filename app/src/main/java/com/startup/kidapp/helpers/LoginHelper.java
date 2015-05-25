package com.startup.kidapp.helpers;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.startup.kidapp.listeners.LoginListener;
import com.startup.kidapp.models.AppError;
import com.startup.kidapp.models.LoginRespone;
import com.startup.kidapp.sync.SyncHTTPService;
import com.startup.kidapp.sync.URLBundle;
import com.startup.kidapp.utils.AppConstants;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by rajendra on 5/24/15.
 */
public class LoginHelper extends AsyncTask<String, Void, String> {

    private static final String TAG=LoginHelper.class.getSimpleName();

    private static LoginListener _listener;

    private static AppError appError;

    private Gson gson  = new Gson();

    private LoginHelper(LoginListener listener) {

        _listener = listener;
    }

    public static void validateLoginCredentials(String userName, String password, LoginListener listener) {

        if (validateInformation(userName, password)) {


            new LoginHelper(listener).execute(userName, password);


        } else {
            appError = new AppError();
            appError.developermessage = " Invalid User Name or Password";
            _listener.onError(appError);
        }

    }

    private static boolean validateInformation(String userName, String password) {
        if (userName != null && !userName.isEmpty() && password != null && !password.isEmpty()) {
            return true;
        }

        return false;

    }

    private String requestLogin(String username, String password) {

        String encodeUser = null;
        String encodePassword = null;


        URLBundle req = null;
        try {

            encodeUser = URLEncoder.encode(username, "utf-8");
            encodePassword = URLEncoder.encode(password, "utf-8");
            String uri = AppConstants.loginURL + "u=" + encodeUser + "&p=" + encodePassword;
            req = new URLBundle(new URL(uri));
            req.setVerb("POST");
            req.addHeader("Content-Type", "application/x-www-form-urlencoded");
            req.addHeader("Accept", "application/json");
            String response = SyncHTTPService.request(req);

            Log.d(TAG, "response "+response);

            return response;

        } catch (IOException e) {
            e.printStackTrace();
            appError = new AppError();
            appError.developermessage = "";
            _listener.onError(appError);
            return null;

        }

    }

    @Override
    protected String doInBackground(String... params) {
        String user = String.valueOf(params[0]);
        String password = String.valueOf(params[1]);

        String response= requestLogin(user, password);
        return response;
    }

    @Override
    protected void onPostExecute(String respone) {
        parseAndSendJSONRespone(respone);
    }


    private void parseAndSendJSONRespone(String respone) {


        if(respone!=null) {

            //JSONObject obj = new JSONObject(respone);
            //String value = obj.getString("info").toString();


            LoginRespone loginresponse = gson.fromJson(respone, LoginRespone.class);

            _listener.onSuccess(loginresponse);


        }
        else {

        }

    }
}
