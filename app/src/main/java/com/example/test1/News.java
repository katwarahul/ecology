package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class News extends AppCompatActivity {
    ListView listView;
    ArrayList<String> titles;
    ArrayList<String> links;
    SharedPreferences sharedPreferences;
    public void SetUrl(String s){
        sharedPreferences.edit().putString("urlss",s).apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().getElevation();

        titles = new ArrayList<String>();
        links = new ArrayList<String>();

        listView = (ListView)findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("str",links.get(position));
                intent.putExtra("title","Environment");
                startActivity(intent);
            }
        });
        new ProcessInBackground().execute();
    }

    public InputStream getInputStream(URL url){
        try {
            return url.openConnection().getInputStream();
        }catch (IOException e){
            return null;
        }
    }

    public class ProcessInBackground extends AsyncTask<Integer,Void,Exception> {

        Exception exception = null;


        ProgressDialog progressDialog = new ProgressDialog(News.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("process....");
            progressDialog.show();
        }
        @Override
        protected Exception doInBackground(Integer... integers) {
            try {
                String urlss = "https://www.sciencedaily.com/rss/top/environment.xml";
                URL url = new URL(urlss);
                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                xmlPullParserFactory.setNamespaceAware(false);
                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
                xmlPullParser.setInput(getInputStream(url),"UTF-8");
                boolean endofile = false;
                int eventType = xmlPullParser.getEventType();


                while (eventType != XmlPullParser.END_DOCUMENT){

                    if(eventType == XmlPullParser.START_TAG){
                        if(xmlPullParser.getName().equalsIgnoreCase("item")){
                            endofile = true;}
                        else if(xmlPullParser.getName().equalsIgnoreCase("title")){
                            if(endofile){
                                String str = xmlPullParser.nextText();
                                if(str.contains("News24.com | ")){
                                    str = str.substring(13);
                                }
                                if(str.contains("Fin24.com | ")){
                                    str = str.substring(12);
                                }
                                if(str.contains("Sport24.co.za | ")){
                                    str = str.substring(17);
                                }
                                titles.add(str);
                            } }
                        else if (xmlPullParser.getName().equalsIgnoreCase("link")){
                            if(endofile) {
                                links.add(xmlPullParser.nextText());
                            }
                        }
                    }
                    else if (eventType == XmlPullParser.END_TAG && xmlPullParser.getName().equalsIgnoreCase("item")){
                        endofile = false;
                    }
                    eventType = xmlPullParser.next();
                }

            }catch (MalformedURLException e){
                exception = e;
            }catch (XmlPullParserException e){
                exception = e;
            }catch (IOException e){
                exception = e;
            }
            return exception;
        }
        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter(News.this,android.R.layout.simple_dropdown_item_1line,titles);
            listView.setAdapter(arrayAdapter);
        }
    }
}