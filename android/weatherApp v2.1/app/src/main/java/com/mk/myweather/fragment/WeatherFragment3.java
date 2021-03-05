
package com.mk.myweather.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.mk.myweather.R;
import com.mk.myweather.util.GetXmlTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class WeatherFragment3 extends Fragment {

    Button getWeather3;
    TextView showWeather3;
    Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_weather3,
                        container, false);

        spinner = rootView.findViewById(R.id.spinner);
        ArrayList<String> arr = new ArrayList<>();
        arr.add("대구광역시");
        arr.add("서울특별시");
        arr.add("부산광역시");
        arr.add("울산광역시");
        arr.add("광주광역시");
        arr.add("대전광역시");
        arr.add("인천광역시");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, arr);
        spinner.setAdapter(arrayAdapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // fragment layout 연결
        getWeather3 = rootView.findViewById(R.id.startWeather3);
        showWeather3 = rootView.findViewById(R.id.weatherInfo3);
        getWeather3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WeatherFragment3.GetXmlTaskFrag3 task = new WeatherFragment3.GetXmlTaskFrag3();
                String text = spinner.getSelectedItem().toString();

                switch (text) {
                    case "대구광역시":
                        task.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=89&gridy=90");
                        break;
                    case "서울특별시":
                        task.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=60&gridy=127");
                        break;
                    case "부산광역시":
                        task.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=98&gridy=76");
                        break;
                    case "울산광역시":
                        task.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=102&gridy=84");
                        break;
                    case "광주광역시":
                        task.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=58&gridy=74");
                        break;
                    case "인천광역시":
                        task.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=55&gridy=124");
                        break;
                    case "대전광역시":
                        task.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=67&gridy=100");
                        break;
                }
            }
        });




        return rootView;
    }

    private class GetXmlTaskFrag3 extends AsyncTask<String, Void, Document> {
        Document doc = null;

        // 작업쓰레드 영역
        @Override
        protected Document doInBackground(String... strings) {
            URL url;
            try {
                url = new URL(strings[0]);
                DocumentBuilderFactory dbf =
                        DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();
            }
            catch (Exception e) {
                Log.d("GetXmlTask","xml에러: " + e.getMessage());
            }
            return doc;
        }

        // 작업쓰레드 종료후 처리 (UI 쓰레드)
        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);

            Calendar cal = Calendar.getInstance();
            int year = cal.get(cal.YEAR);
            int month = cal.get(cal.MONTH) + 1;
            int date = cal.get(cal.DATE);
            String strDate = null;

            String s = "";
            NodeList nodeList = doc.getElementsByTagName("data");

            for (int i=0; i<nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Element fstElmnt = (Element) node;
                // 날짜 : 2020년 8월 13일
                // 시간, 날씨, 온도, 습도, 강수확률,

                NodeList day = fstElmnt.getElementsByTagName("day");
                Element dayElement = (Element) day.item(0);
                day = dayElement.getChildNodes();
                switch (day.item(0).getNodeValue()) {
                    case "0": // 오늘
                        strDate = String.format("%d년 %d월 %d일",
                                year, month, date);
                        Log.d("onPostExecute", "오늘날짜: " + strDate);
                        break;
                    case "1": // 내일
                        strDate = String.format("%d년 %d월 %d일",
                                year, month, date + 1);
                        Log.d("onPostExecute", "내일날짜: " + strDate);
                        break;
                    case "2": // 모레
                        strDate = String.format("%d년 %d월 %d일",
                                year, month, date + 2);
                        Log.d("onPostExecute", "모레날짜: " + strDate);
                        break;
                }
                s += strDate + " ";

                NodeList hourList = fstElmnt.getElementsByTagName("hour");
                Element hourElement = (Element) hourList.item(0);
                hourList = hourElement.getChildNodes();
                String time = hourList.item(0).getNodeValue();
                s += time + "시 \n";

                NodeList tempList = fstElmnt.getElementsByTagName("temp");
                Element tempElement = (Element) tempList.item(0);
                tempList = tempElement.getChildNodes();
                String temp = tempList.item(0).getNodeValue();
                s += "온도 : " + temp + "도   ";

                NodeList weatherList = fstElmnt.getElementsByTagName("wfKor");
                Element weatherElement = (Element) weatherList.item(0);
                weatherList = weatherElement.getChildNodes();
                String weather = weatherList.item(0).getNodeValue();
                s += "날씨 : " + weather + "\n";

                NodeList rehList = fstElmnt.getElementsByTagName("reh");
                Element rehElement = (Element) rehList.item(0);
                rehList = rehElement.getChildNodes();
                String humi = rehList.item(0).getNodeValue();
                s += "습도 : " + humi + "%   ";

                NodeList popList = fstElmnt.getElementsByTagName("pop");
                Element popElement = (Element) popList.item(0);
                popList = popElement.getChildNodes();
                String pop = popList.item(0).getNodeValue();
                s += "강수확률 : " + pop + "% \n\n";

            }
            showWeather3.setText(s);
        }
    }

}