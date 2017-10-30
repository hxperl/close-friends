package com.example.hxperl.closefriends0_1v;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class FragMain extends Activity{

    FragmentManager manager;
    FragmentTransaction trans;
    FragmentA fa;
    FragmentB fb = new FragmentB();
    FragmentC fc = new FragmentC();
    FragmentD fd = new FragmentD();

    TextView nickinfo;
    TextView ageinfo;
    TextView sexinfo;
    TextView nickLabel2;
    TextView ageLabel2;
    TextView sexLabel;
    TextView interLabel;
    TextView interLable2;
    TextView interinfo;
    Spinner spinner;
    Button modbtn;
    Button addbtn;
    TextView editfriend;
    ImageView imageView;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        Button btn_a = (Button)findViewById(R.id.btn_a);
        Button btn_b = (Button)findViewById(R.id.btn_b);
        Button btn_c = (Button)findViewById(R.id.btn_c);
        Button btn_d = (Button)findViewById(R.id.btn_d);

        nickinfo = (TextView)findViewById(R.id.nickinfo);
        ageinfo = (TextView)findViewById(R.id.ageinfo);
        sexinfo = (TextView)findViewById(R.id.sexinfo);
        nickLabel2 = (TextView)findViewById(R.id.nicknameLable2);
        ageLabel2 = (TextView)findViewById(R.id.ageLable2);
        sexLabel = (TextView)findViewById(R.id.sexLable);
        modbtn = (Button)findViewById(R.id.modbtn);
        imageView = (ImageView)findViewById(R.id.imageView);
        interLabel = (TextView)findViewById(R.id.interLable);
        interLable2 = (TextView)findViewById(R.id.interLable2);
        interinfo = (TextView)findViewById(R.id.interinfo);
        spinner = (Spinner)findViewById(R.id.spinner);
        editfriend = (TextView)findViewById(R.id.editfriend);
        addbtn = (Button)findViewById(R.id.addbtn);


        manager = getFragmentManager();

        fa = (FragmentA)manager.findFragmentById(R.id.frg_a);




        btn_a.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                showA();
                trans = manager.beginTransaction();
                trans.replace(R.id.frg_a, fa);
                trans.commit();
            }
        });
        btn_b.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                hideA();
                trans = manager.beginTransaction();
                trans.replace(R.id.frg_a, fb);
                trans.commit();
            }
        });
        btn_c.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                hideA();
                trans = manager.beginTransaction();
                trans.replace(R.id.frg_a, fc);
                trans.commit();
            }
        });

        btn_d.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                hideA();
                trans = manager.beginTransaction();
                trans.replace(R.id.frg_a, fd);
                trans.commit();

            }
        });





    }

    public void hideA() {

        nickinfo.setVisibility(View.INVISIBLE);
        ageinfo.setVisibility(View.INVISIBLE);
        sexinfo.setVisibility(View.INVISIBLE);
        nickLabel2.setVisibility(View.INVISIBLE);
        ageLabel2.setVisibility(View.INVISIBLE);
        sexLabel.setVisibility(View.INVISIBLE);
        modbtn.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        interLable2.setVisibility(View.INVISIBLE);
        interLabel.setVisibility(View.INVISIBLE);
        interinfo.setVisibility(View.INVISIBLE);
        spinner.setVisibility(View.INVISIBLE);
        addbtn.setVisibility(View.INVISIBLE);
        editfriend.setVisibility(View.INVISIBLE);
    }

    public void showA() {
        nickinfo.setVisibility(View.VISIBLE);
        ageinfo.setVisibility(View.VISIBLE);
        sexinfo.setVisibility(View.VISIBLE);
        nickLabel2.setVisibility(View.VISIBLE);
        ageLabel2.setVisibility(View.VISIBLE);
        sexLabel.setVisibility(View.VISIBLE);
        modbtn.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        interLable2.setVisibility(View.VISIBLE);
        interLabel.setVisibility(View.VISIBLE);
        interinfo.setVisibility(View.VISIBLE);
        spinner.setVisibility(View.VISIBLE);
        editfriend.setVisibility(View.VISIBLE);
        addbtn.setVisibility(View.VISIBLE);
    }





}