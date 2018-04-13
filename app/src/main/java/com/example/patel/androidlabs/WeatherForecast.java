package com.example.patel.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherForecast extends Activity {


    protected static final String TAG = "WeatherForecast";
    ProgressBar weapgbr =(ProgressBar)findViewById(R.id.progressBar);

    TextView speed1;
    TextView min1;
    TextView max1;
    TextView current_temprature1;
    ImageView weatherImg1;


    ForecastQuery weather = new ForecastQuery();

    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        weapgbr.setVisibility(View.VISIBLE);

        current_temprature1 = (TextView) findViewById(R.id.c_temp);
        min1 = (TextView) findViewById(R.id.min_temp);
        max1= (TextView) findViewById(R.id.max_temp);
        weatherImg1 = (ImageView) findViewById(R.id.imageView3);
        speed1=(TextView)findViewById(R.id.wind_speed);


    }

    public class ForecastQuery extends AsyncTask<String,Integer,String> {


        String current_temprature;
        String min;
        String max;
        String speed;
        String weatherImg;
        private Bitmap bitmap;
        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... strings) {
            try {
                for(String siteUrl: strings) {
                    URL url = new URL(siteUrl);

                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream iStream = urlConnection.getInputStream();

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(false);
                    XmlPullParser xpp= factory.newPullParser();
                    xpp.setInput( iStream  , "UTF-8");

//start reading:
                    int type;
                    //While you're not at the end of the document:
                    while((type = xpp.getEventType()) != XmlPullParser.END_DOCUMENT)
                    {
                        //Are you currently at a Start Tag?
                        if(xpp.getEventType() == xpp.START_TAG)
                        {
                            //Is it the "AMessage" tag?
                            if(xpp.getName().equals("temperature") )
                            {
                                current_temprature =xpp.getAttributeValue(null, "value");
                                Log.i("Current Temperature:" , current_temprature );
                                publishProgress(25);
                                min =xpp.getAttributeValue(null, "min");
                                Log.i("Minimum Temperature:" , min);
                                publishProgress(50);
                                max =xpp.getAttributeValue(null, "max");
                                Log.i("Maximum Temperature:" , max);
                                publishProgress(75);
                            }
                            //Is it the Weather tag?
                            else if(xpp.getName().equals("speed") )
                            {
//                            FileOutputStream of = openFileOutput("XMLData", Context.MODE_PRIVATE);
                                speed =xpp.getAttributeValue(null, "value");
                                Log.i("Wind Speed: " , speed );
                            }
                            else if(xpp.getName().equals("weather")){
                                weatherImg = xpp.getAttributeValue(null, "icon");
                                String iconFile = weatherImg+".png";
                                if(fileExist(iconFile)){
                                    FileInputStream fis = null;
                                    try {    fis = openFileInput(iconFile);   }
                                    catch (FileNotFoundException e) {    e.printStackTrace();  }
                                    bitmap = BitmapFactory.decodeStream(fis);

                                } else {
                                    URL iconUrl = new URL("https://openweathermap.org/img/w/" + weatherImg + ".png");
                                    bitmap  = HttpUtils.getImage(iconUrl);
                                    FileOutputStream outputStream = openFileOutput( weatherImg + ".png", Context.MODE_PRIVATE);
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                                    outputStream.flush();
                                    outputStream.close();


                                }
                                publishProgress(100);
                            }
                        }
                        // Go to the next XML event
                        xpp.next();
                    }
                }


            }catch (Exception mfe)
            {
                Log.e("Error", mfe.getMessage());
            }

            return "Finished";

        }

        //when GUI is ready, update the objects
        public void onProgressUpdate(Integer ... data)
        {
            weapgbr.setVisibility(View.VISIBLE);
            weapgbr.setProgress(data[0]);
        }
        public boolean fileExist(String name){
            File file = getBaseContext().getFileStreamPath(name);
            return file.exists();
        }
        //gui thread
        public void onPostExecute(String result)
        {
            current_temprature1.setText(current_temprature1.getText()+current_temprature);
            min1.setText(min1.getText()+min);
            max1.setText(max1.getText()+ max);
            speed1.setText(speed1.getText()+speed);

            weatherImg1.setImageBitmap(bitmap);
            weapgbr.setVisibility(View.INVISIBLE);

        }
    }

}
class HttpUtils {
    public static Bitmap getImage(URL url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                return BitmapFactory.decodeStream(connection.getInputStream());
            } else
                return null;
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    public static Bitmap getImage(String urlString) {
        try {
            URL url = new URL(urlString);
            return getImage(url);
        } catch (MalformedURLException e) {
            return null;
        }
    }}
