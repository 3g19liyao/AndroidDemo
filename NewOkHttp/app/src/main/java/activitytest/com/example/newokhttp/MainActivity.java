package activitytest.com.example.newokhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.ContentHandler;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendRequest = (Button)findViewById(R.id.send_request);
        responseText = (TextView)findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.send_request){
            sendRequestWithOkHttp();
        }
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                        .url("http://localhost:8888/get_data.xml")
                        .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().toString();
                    //showResponse(responseData);
                    //parseXMLWithPull(responseData);
                    //parseXMLWithSAX(responseData);
                    //parseJSONWithJSONObject(responseData);
                    parseJSONWithGSON(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>(){}.getType());
        for(App app:appList){
            Log.d("MainActivity","id is "+app.getId());
            Log.d("MainActivity","name is "+app.getName());
            Log.d("MainActivity","version is "+app.getVersion());
        }
    }
    private void parseJSONWithJSONObject(String jsonData){

        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for(int i=0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d("MainActivity","id is " + id);
                Log.d("MainActivity","name is " + name);
                Log.d("MainActivity","version is " + version);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void parseXMLWithSAX(String xmlData){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        XMLReader xmlReader = null;
        try {
            xmlReader = factory.newSAXParser().getXMLReader();
            activitytest.com.example.newokhttp.ContentHandler handler = new activitytest.com.example.newokhttp.ContentHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void parseXMLWithPull(String xmlData) {

        try {
            XmlPullParserFactory factory = null;
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while(eventType != XmlPullParser.END_DOCUMENT){
                String nodename = xmlPullParser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:{
                        if("id".equals(nodename)){
                            id = xmlPullParser.nextText();
                        }else if("name".equals(nodename)){
                            name = xmlPullParser.nextText();
                        }else if("version".equals(nodename)){
                            version = xmlPullParser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG: {
                        if("app".equals(name)){
                            Log.d("MainActivity","id is" + id);
                            Log.d("MainActivity","name is" + name);
                            Log.d("MainActivity","version is" + version);
                        }
                        break;
                    }
                    default:break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
    private void showResponse(final String response){
        runOnUiThread(new Runnable() {                          //安卓不允许子线程UI操作，这个会切换到主线程
            @Override
            public void run() {
                responseText.setText(response);
            }
        });
    }
}