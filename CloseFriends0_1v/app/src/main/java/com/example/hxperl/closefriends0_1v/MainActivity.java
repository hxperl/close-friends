package com.example.hxperl.closefriends0_1v;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.loopj.android.http.*;


public class MainActivity extends Activity {

    public static final int REQUEST_CODE_JOIN = 1001;
    public static final int REQUEST_CODE_LOGIN = 1002;
    public static String uid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = (Button)findViewById(R.id.loginBtn);
        Button exitBtn = (Button)findViewById(R.id.exitBtn);
        Button joinBtn = (Button)findViewById(R.id.joinBtn);
        final EditText usernameEntry = (EditText)findViewById(R.id.usernameEntry);
        final EditText passwordEntry = (EditText)findViewById(R.id.passwordEntry);

        AsyncHttpClient client = HttpClient.getInstance();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                uid=usernameEntry.getText().toString();
                RequestParams params = new RequestParams();
                params.put("id", usernameEntry.getText().toString());
                params.put("pwd", passwordEntry.getText().toString());

                HttpClient.get("", params, new AsyncHttpResponseHandler() {
                    public void onSuccess(String response) {
                        if (response.equals("success")) {
                            Intent loginIntent = new Intent(getBaseContext(), FragMain.class);
                            startActivityForResult(loginIntent, REQUEST_CODE_LOGIN);
                        }
                        else if(response.equals("fail1"))
                        {
                            Toast.makeText(getApplicationContext(), "ID를 찾을 수 없습니다.", Toast.LENGTH_LONG).show();
                        }
                        else if(response.equals("fail2"))
                        {
                            Toast.makeText(getApplicationContext(), "패스워드가 틀렸습니다.", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "알 수 없는 에러!", Toast.LENGTH_LONG).show();
                    }
                });



            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(getBaseContext(), Register.class);
                startActivityForResult(regIntent, REQUEST_CODE_JOIN);

            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}