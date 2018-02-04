package codeutsava.app.codeutsava.com.codeutsava;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import codeutsava.app.codeutsava.com.codeutsava.helper.AppController;
import codeutsava.app.codeutsava.com.codeutsava.helper.ConnectivityReceiver;
import codeutsava.app.codeutsava.com.codeutsava.helper.SQLiteHandler;
import codeutsava.app.codeutsava.com.codeutsava.helper.SessionManager;
import codeutsava.app.codeutsava.com.codeutsava.helper.User;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private SessionManager session;
    private SQLiteHandler db;
    private EditText etUsername;
    private EditText etPassword;
    private Button bLogin;
    private TextView forgotpassword;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (!ConnectivityReceiver.isConnected())
            Snackbar.make(findViewById(R.id.login), "Connection Error", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Login");
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/

        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        pDialog = new ProgressDialog(LoginActivity.this);

        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            SQLiteHandler sqLiteHandler = new SQLiteHandler(getApplicationContext());
            User user = sqLiteHandler.getUser();
            Intent intent = new Intent(LoginActivity.this, CheckinActivity.class);
            startActivity(intent);
            finish();
        }
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        forgotpassword = (TextView) findViewById(R.id.forgotpassword);

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etUsername.getText().toString().trim();
                setForgotpassword();

            }
        });
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String email = etUsername.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();
                loginUser(email, password);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void loginUser(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";
        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                "http://192.168.225.32:9000/login", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("ayush", "Login Response: " + response.toString());
                hideDialog();
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean error = jsonResponse.getBoolean("error");
                    if (!error) {
                        int user_id = 1;
                        String last_name = jsonResponse.getString("lname");
                        String first_name = jsonResponse.getString("fname");
                        String email = jsonResponse.getString("email");
                        String phone = jsonResponse.getString("phone");
                        session = new SessionManager(getApplicationContext());
                        session.setLogin(true);
                        Intent intent = new Intent(LoginActivity.this, CheckinActivity.class);
                        startActivity(intent);

                        finish();
                    } else {
                        if (jsonResponse.has("message")) {
                            new AlertDialog.Builder(LoginActivity.this).setIcon(R.drawable.ic_dialog_alert).setTitle("Message")
                                    .setMessage(jsonResponse.getString("error_msg"))
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }).show();

                        } else {
                            Snackbar.make(findViewById(R.id.login), jsonResponse.getString("message"), Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Log.e(TAG, "Login Error: " + error.getMessage());
                Snackbar.make(findViewById(R.id.login), "Error!! Please try again later..", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", email);
                params.put("password", password);
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void setForgotpassword() {
        // Tag used to cancel the request

        AlertDialog.Builder homeScreenDialog = new AlertDialog.Builder(LoginActivity.this);

        LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 0, 20, 0);

        final EditText emailid = new EditText(getApplicationContext());
        emailid.setBackgroundColor(Color.parseColor("#ffffff"));
        emailid.setTextColor(Color.parseColor("#000000"));
        emailid.setHint("Email");
        emailid.setHintTextColor(Color.parseColor("#BDBDBD"));
        layout.addView(emailid, layoutParams);

        homeScreenDialog.setMessage("Enter Email Id :");
        homeScreenDialog.setTitle("Forgot Password");
        homeScreenDialog.setView(layout);
        homeScreenDialog.setCancelable(false);
        homeScreenDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                final String emailids = emailid.getText().toString().trim();
                if (!emailids.isEmpty()) {
                    // login user
                    String tag_string_req = "req_forgotPassword";
                    StringRequest strReq = new StringRequest(Request.Method.POST,
                            "http://192.168.225.32:9000/forgotpassword", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Log.d(TAG, "Login Response: " + response.toString());
                            hideDialog();
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean error = jsonResponse.getBoolean("error");
                                if (!error) {
                                    Snackbar.make(findViewById(R.id.login), "Please check your e-mail for resetting password.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                } else {
                                    Snackbar.make(findViewById(R.id.login), jsonResponse.getString("error_msg"), Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Log.e(TAG, "Login Error: " + error.getMessage());
                            hideDialog();
                            Snackbar.make(findViewById(R.id.login), "Poor Conection!!!", Snackbar.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            // Posting parameters to login url
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("email", emailids);
                            return params;
                        }
                    };
                    // Adding request to request queue
                    AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
                } else {
                    // Prompt user to enter credentials
                    Snackbar.make(findViewById(R.id.login), "Please enter your email address.", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        homeScreenDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        homeScreenDialog.show();

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
