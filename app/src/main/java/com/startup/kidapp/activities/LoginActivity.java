package com.startup.kidapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.startup.kidapp.R;
import com.startup.kidapp.helpers.LoginHelper;
import com.startup.kidapp.listeners.LoginListener;
import com.startup.kidapp.models.AppError;
import com.startup.kidapp.models.LoginRespone;


/**
 * Created by rajendra on 5/24/15.
 */
public class LoginActivity extends Activity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);

        checkLoginCredentials();
    }


    public void checkLoginCredentials() {


        LoginHelper.validateLoginCredentials("admin","123", new LoginListener() {
            @Override
            public void onSuccess(LoginRespone respone) {
                Log.d(TAG, "respone " + respone);

            }

            @Override
            public void onError(AppError error) {

                Log.e(TAG,"error "+error.developermessage);
            }
        });

    }

}
