package com.example.hxperl.closefriends0_1v;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentA extends Fragment implements AdapterView.OnItemSelectedListener{

    String []strings;
    Button modbtn;
    Button addbtn;
    EditText nickinfo;
    EditText ageinfo;
    EditText editfriend;
    TextView interinfo;
    TextView sexinfo;
    String index;
    int integer_info;
    Spinner spinner;
    ArrayAdapter<String> adapter;
    int position_info;

    public static final int REQUEST_CODE_ADD = 1003;


    static MainActivity ma = new MainActivity();

    String[] items ={"없음","운동", "연애", "요리", "게임", "독서", "음악"};

    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_a, container, false);


        addbtn = (Button)view.findViewById(R.id.addbtn);
        spinner = (Spinner)view.findViewById(R.id.spinner);
        nickinfo = (EditText)view.findViewById(R.id.nickinfo);
        ageinfo = (EditText)view.findViewById(R.id.ageinfo);
        interinfo = (TextView)view.findViewById(R.id.interinfo);
        sexinfo = (TextView)view.findViewById(R.id.sexinfo);
        modbtn = (Button)view.findViewById(R.id.modbtn);
        editfriend = (EditText)view.findViewById(R.id.editfriend);

        spinner.setOnItemSelectedListener(this);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        RequestParams params = new RequestParams();
        params.put("id",ma.uid);

        HttpClient3.get("", params, new AsyncHttpResponseHandler() {
            public void onSuccess(String response) {

                strings = response.split("[+]");
                nickinfo.setText(strings[0]);
                ageinfo.setText(strings[1]);
                //성별 부분
                if (strings[2].equals("1"))
                    sexinfo.setText("남");
                else
                    sexinfo.setText("여");

                integer_info = Integer.parseInt(strings[3]);

                interinfo.setText(items[integer_info]);


            }
        });



        modbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   age = Integer.parseInt(ageinfo.getText().toString());
                RequestParams params = new RequestParams();
                params.put("id", ma.uid);
                params.put("nickname", nickinfo.getText().toString());
                params.put("age", ageinfo.getText().toString());
                params.put("inter", index);

                HttpClient4.get("", params, new AsyncHttpResponseHandler() {
                    public void onSuccess(String response) {
                        if (response.equals("success")) {
                            interinfo.setText(items[position_info]);
                        } else {
                            Toast.makeText(getActivity(), "알수 없는 에러!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams();
                params.put("id", ma.uid);
                params.put("fid", editfriend.getText().toString());
                HttpClient6.get("", params, new AsyncHttpResponseHandler() {
                    public void onSuccess(String response) {
                        if(response.equals("error01"))
                            Toast.makeText(getActivity(), "ID를 찾을 수 없습니다.", Toast.LENGTH_LONG).show();
                        else if(response.equals("error02"))
                            Toast.makeText(getActivity(), "이미 추가 된 ID입니다.", Toast.LENGTH_LONG).show();
                        else if(response.equals("success"))
                            Toast.makeText(getActivity(), "친구 추가 완료.", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getActivity(), "알수 없는 에러", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        return view;
    }


    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
      //  interinfo.setText(items[position]);
        index = String.valueOf(position);
        position_info = position;
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}