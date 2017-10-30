package com.example.hxperl.closefriends0_1v;

import android.app.Fragment;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class FragmentD extends Fragment implements GoogleMap.OnMapClickListener,  AdapterView.OnItemSelectedListener{

    GoogleMap mMap;
    MapView mapView;
    GpsHandler handler;
    GpsRunnable gpsRunnable;
    boolean isRunning = false;
    MainActivity ma = new MainActivity();

    Spinner spinner2;
    ArrayAdapter<String> adapter2;
    String[] items2 ={"전체 보기", "친구만", "공통 관심사"};
    String[] strings;
    String string;
    int count;
    int i;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new GpsHandler();
        gpsRunnable = new GpsRunnable();
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_d, container, false);

        spinner2 = (Spinner)view.findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);
        adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);


        mapView = (MapView)view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        mMap = mapView.getMap();
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.setMyLocationEnabled(true);

        MapsInitializer.initialize(this.getActivity());

        return view;
    }

    public class GpsHandler extends Handler {

        public void handleMessage(Message msg) {
            mMap.clear();
            GpsInfo gps = new GpsInfo(getActivity());

            // GPS 사용유무 가져오기
            if (gps.isGetLocation()) {
                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

                RequestParams params = new RequestParams();
                params.put("id", ma.uid);
                params.put("lat", String.valueOf(latitude));
                params.put("long", String.valueOf(longitude));
                HttpClient8.get("", params, new AsyncHttpResponseHandler() {
                    public void onSuccess(String response) {
                        return;
                    }
                });


                // Creating a LatLng object for the current location
                LatLng latLng = new LatLng(latitude, longitude);

                // Showing the current location in Google Map
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                // Map 을 zoom 합니다.
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));




                // 마커 설정.
                MarkerOptions optFirst = new MarkerOptions();
                optFirst.position(latLng);// 위도 • 경도
                optFirst.title("Me");// 제목 미리보기
                optFirst.snippet("내 위치");
//                optFirst.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
                mMap.addMarker(optFirst).showInfoWindow();


                strings = string.split("[+]");

                i=0;

                MarkerOptions opt1 = new MarkerOptions();
                LatLng latLng1;
                while(!strings[i].equals("end")) {
                    latLng1 = new LatLng(Double.valueOf(strings[i]), Double.valueOf(strings[i + 1]));
                    opt1.position(latLng1);
                    if(strings[i+4].equals("1"))
                        strings[i+4]="♂";
                    else
                        strings[i+4]="♀";
                    opt1.title(strings[i + 2]);
                    opt1.snippet(strings[i + 3] + strings[i + 4]);
     //               opt1.icon(BitmapDescriptorFactory.fromResource(R.drawable.seolhyun));
                    mMap.addMarker(opt1);
                    i=i+6;
                }



            }
        }
    }

    public class GpsRunnable implements Runnable {
        public void run() {
            try{
                for (int i=0 ; i<20 && isRunning; i++) {
                    Thread.sleep(10000);
                    RequestParams params2 = new RequestParams();
                    params2.put("id", ma.uid);
                    HttpClient9.get("", params2, new AsyncHttpResponseHandler() {
                        public void onSuccess(String response) {
                            string = response;
                        }
                    });

                    Message msg = handler.obtainMessage();
                    handler.sendMessage(msg);
                }
            }catch (Exception ex) {
                Log.e("SampleThreadActivity", "Exception in processing message");
            }

        }
    }


    public void onMapClick(LatLng point) {

        // 현재 위도와 경도에서 화면 포인트를 알려준다
        Point screenPt = mMap.getProjection().toScreenLocation(point);

        // 현재 화면에 찍힌 포인트로 부터 위도와 경도를 알려준다.
        LatLng latLng = mMap.getProjection().fromScreenLocation(screenPt);

        Log.d("맵좌표", "좌표: 위도(" + String.valueOf(point.latitude) + "), 경도("
                + String.valueOf(point.longitude) + ")");
        Log.d("화면좌표", "화면좌표: X(" + String.valueOf(screenPt.x) + "), Y("
                + String.valueOf(screenPt.y) + ")");
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
     //   position_info = position;

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }



    public void onStart() {
        super.onStart();
        Thread thread1 = new Thread(gpsRunnable);

        isRunning = true;
        thread1.start();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    public void onStop() {
        super.onStop();
        isRunning = false;
    }

}