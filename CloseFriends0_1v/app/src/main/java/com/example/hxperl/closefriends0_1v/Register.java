package com.example.hxperl.closefriends0_1v;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

public class Register extends Activity {

    RadioGroup radio;
    String username2;
    String password2;
    String password3;
    String nickname;
    String age_st;
    int sex=0;
    String sex_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Button joinBtn2 = (Button)findViewById(R.id.joinBtn2);
        Button exitBtn2 = (Button)findViewById(R.id.exitBtn2);
        radio = (RadioGroup)findViewById(R.id.radioGroup);
        final EditText usernameEntry2 = (EditText)findViewById(R.id.usernameEntry2);
        final EditText passwordEntry2 = (EditText)findViewById(R.id.passwordEntry2);
        final EditText passwordEntry3 = (EditText)findViewById(R.id.passwordEntry3);
        final EditText nicknameEntry = (EditText)findViewById(R.id.nicknameEntry);
        final EditText ageEntry = (EditText)findViewById(R.id.ageEntry);


        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(group==radio) {
                    if(checkedId == R.id.male)
                        sex=1;

                    if(checkedId == R.id.female)
                        sex=2;
                }
            }
        });


        joinBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username2 = usernameEntry2.getText().toString();
                password2 = passwordEntry2.getText().toString();
                password3 = passwordEntry3.getText().toString();
                nickname = nicknameEntry.getText().toString();
                age_st = ageEntry.getText().toString();
                sex_s = String.valueOf(sex);

                if(username2.length()<=0 || password2.length() <=0 || password3.length() <=0 ||
                    nickname.length() <=0 || age_st.length() <=0 )
                    Toast.makeText(getApplicationContext(), "빈칸 없이 입력해주세요", Toast.LENGTH_LONG).show();
                else if(!password2.equals(password3))
                    Toast.makeText(getApplicationContext(), "패스워드가 서로 다릅니다.", Toast.LENGTH_LONG).show();
                else if(sex==0)
                    Toast.makeText(getApplicationContext(), "성별을 선택해주세요", Toast.LENGTH_LONG).show();
                else
                {
                    RequestParams params = new RequestParams();
                    params.put("id", username2);
                    params.put("pwd2", password2);
                    params.put("nickname", nickname);
                    params.put("age", age_st);
                    params.put("sex", sex_s);

                    HttpClient2.get("", params, new AsyncHttpResponseHandler() {
                        public void onSuccess(String response) {
                            if(response.equals("success"))
                            {
                                Toast.makeText(getApplicationContext(),"회원가입완료!", Toast.LENGTH_LONG).show();
                                finish();
                            }
                            else if(response.equals("fail1")){
                                Toast.makeText(getApplicationContext(),"이미 존재하는 ID입니다.", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"알수 없는 에러!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }




            }
        });

        exitBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
