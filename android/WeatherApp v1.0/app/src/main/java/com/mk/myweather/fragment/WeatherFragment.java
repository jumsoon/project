package com.mk.myweather.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mk.myweather.R;
import com.mk.myweather.activity.MainActivity;
import com.mk.myweather.adapter.WeatherAdapter;
import com.mk.myweather.util.GetXmlTask;
import com.mk.myweather.util.NetworkUtill;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class WeatherFragment extends Fragment {
//    TextView kangwon, kyongki, inchun, kyongnam, kyongpook, kangju;
//    TextView chungpook, chungnam, daegu, daejun, ulsan, pusan, junnam;
//    TextView junpook, jeju, seoul;



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_weather,
                        container, false);

        // fragment layout 연결
//        kangwon = rootView.findViewById(R.id.kangwon);
//        kangwon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String str = NetworkUtill.getConnectedStatusString(getActivity());
//                if(str.equals("no_network")) {
//                    Toast.makeText(getActivity(), "네트워크 연결을 확인하세요",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                GetXmlTask task = new GetXmlTask((MainActivity) getActivity(), "강원도");
//                task.execute(
//                        "https://www.kma.go.kr/wid/queryDFS.jsp?gridx=73&gridy=134");
//                Toast.makeText(getActivity(), "강원도", Toast.LENGTH_SHORT).show();
//            }
//        });

        return rootView;
    }


}