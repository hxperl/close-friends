package com.example.hxperl.closefriends0_1v;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ListView;

import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class FragmentB extends Fragment{


    MainActivity ma = new MainActivity();
    ListView listView1;
    IconTextListAdapter adapter;


    String []strings_f;


    int count=0;
    int i;
    Resources res;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_b, container, false);

        listView1 = (ListView)view.findViewById(R.id.listView1);

        adapter = new IconTextListAdapter(getActivity());

        res = getResources();

        RequestParams params = new RequestParams();
        params.put("id", ma.uid);
        HttpClient5.get("", params, new AsyncHttpResponseHandler() {
            public void onSuccess(String response) {
                if (response.equals("fail0"))
                    return;
                strings_f = response.split("[+]");
                count = Integer.parseInt(strings_f[0]);
            }
        });

        if(count==0)
        {
            return view;
        }
        else {
            i=1;
            while(!strings_f[i].equals("end"))
            {
                if(strings_f[i+2].equals("1"))
                    strings_f[i+2]="♂";
                else
                    strings_f[i+2]="♀";
                adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.seolhyun), strings_f[i], strings_f[i+1], strings_f[i+2]));
                i=i+4;
            }

        }




        listView1.setAdapter(adapter);

        // 새로 정의한 리스너로 객체를 만들어 설정
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IconTextItem curItem = (IconTextItem) adapter.getItem(position);
                String[] curData = curItem.getData();

                Toast.makeText(getActivity(), "Selected : " + curData[0], Toast.LENGTH_LONG).show();

            }

        });





        return view;
    }






}