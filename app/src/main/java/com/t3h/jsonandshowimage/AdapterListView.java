package com.t3h.jsonandshowimage;

import android.content.Context;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AdapterListView extends BaseAdapter {

    Context context;
    List<Comics> list;


    public AdapterListView(Context context, List<Comics> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.iteminlistview, null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imv_image);
        TextView textViewName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView textViewAuthor = (TextView) convertView.findViewById(R.id.tv_author);
        Button buttonDetail = (Button) convertView.findViewById(R.id.btn_detail);

        textViewName.setText(list.get(position).getName());
        textViewAuthor.setText(list.get(position).getAuthor());

        //DownloadImageTask downloadImageTask = new DownloadImageTask(imageView);

        //downloadImageTask.execute(list.get(position).getImage());
        //downloadImageTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, list.get(position).getImage());
        Glide.with(context)
                .load(list.get(position).getImage())
                //.override(200,200)
                .into(imageView);
        buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getLink()));
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        private ImageView imageView;

        public DownloadImageTask(ImageView imageView)  {
            this.imageView= imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String imageUrl = params[0];

            InputStream in = null;
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect();

                int resCode = httpConn.getResponseCode();

                if (resCode == HttpURLConnection.HTTP_OK) {
                    in = httpConn.getInputStream();
                } else {
                    return null;
                }

                Bitmap bitmap = BitmapFactory.decodeStream(in);
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        // When the task is completed, this method will be called
        // Download complete. Lets update UI
        @Override
        protected void onPostExecute(Bitmap result) {
            if(result  != null) {
                this.imageView.setImageBitmap(result);
            }
        }
    }
}
