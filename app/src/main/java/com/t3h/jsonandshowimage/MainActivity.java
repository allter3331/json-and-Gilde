package com.t3h.jsonandshowimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        GetComics getComics = new GetComics();
        getComics.execute();


    }

    private class GetComics extends AsyncTask<Void, Void, List<Comics>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();
        }

        @Override
        protected List<Comics> doInBackground(Void... arg0) {
            HttpHandler httpHandler = new HttpHandler();

            String url = "http://144.202.9.52/api/getAllStoryOfCategoryV2/EN/Action";
            String jsonStr = httpHandler.getJsonString(url);

            Log.d(TAG, "Response from url: " + jsonStr);

            List<Comics> list = new ArrayList<>();

            if (jsonStr != null) {
                try {
                    list = readComicsJSONFile(getApplicationContext(), jsonStr);

                    for (Comics comics :  list) {
                        System.out.println(comics.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return list;
        }

        @Override
        protected void onPostExecute(List<Comics> result) {

            final AdapterListView adapter = new AdapterListView(MainActivity.this, result);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(MainActivity.this, adapter.getItem(position)+"", Toast.LENGTH_SHORT).show();

                    Comics comics = (Comics) adapter.getItem(position);
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("detail", comics.toString());

                    startActivity(intent);
                }
            });

        }
    }

    public static List<Comics> readComicsJSONFile(Context context, String jsonStr) throws IOException,JSONException {


        JSONArray jsonarray = new JSONArray(jsonStr);
        List<Comics> comicsList = new ArrayList<>();

        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);

            int category_id = jsonobject.getInt("category_id");
            int story_id = jsonobject.getInt("story_id");
            int id = jsonobject.getInt("id");
            String name = jsonobject.getString("name");
            String author = jsonobject.getString("author");
            int status = jsonobject.getInt("status");
            String kind = jsonobject.getString("kind");
            String image = jsonobject.getString("image");
            String link = jsonobject.getString("link");
            String content = jsonobject.getString("content");
            int trend = jsonobject.getInt("trend");
            int update = jsonobject.getInt("update");
            int check = jsonobject.getInt("check");

            Comics comics = new Comics();
            comics.setCategoryId(category_id);
            comics.setStoryId(story_id);
            comics.setId(id);
            comics.setName(name);
            comics.setAuthor(author);
            comics.setStatus(status);
            comics.setKind(kind);
            comics.setImage(image);
            comics.setLink(link);
            comics.setContent(content);
            comics.setTrend(trend);
            comics.setUpdate(update);
            comics.setCheck(check);

            comicsList.add(comics);
        }

        return comicsList;


    }

}
