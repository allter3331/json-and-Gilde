package com.t3h.jsonandshowimage;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.ProgressDialog;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import android.widget.ImageButton;
import android.widget.TextView;

import com.t3h.jsonandshowimage.adapter.StoryAdapter;
import com.t3h.jsonandshowimage.network.RetrofitApi;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Main2Activity extends AppCompatActivity implements OnLongClickItem{
    private RecyclerView rcvStory;
    private List<ModelTest> arrStory = new ArrayList<>();
    private StoryAdapter adapter;
    Toolbar toolbar;
    TextView textViewToolbar;
    ImageButton imageButtonBack;
    public static boolean isInActionMode = false;
    private List<ModelTest> selectionList = new ArrayList<>();
    private int counter = 0;
    public int position = -1;

    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toolbar = findViewById(R.id.tb_toolbar);
        setSupportActionBar(toolbar);
        textViewToolbar = findViewById(R.id.tv_toolbar);
        textViewToolbar.setVisibility(View.GONE);
        imageButtonBack = findViewById(R.id.btn_back);


        rcvStory = findViewById(R.id.rcv_story);
        rcvStory.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        rcvStory.setLayoutManager(mLayoutManager);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        //GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3,RecyclerView.VERTICAL,false);

        rcvStory.setLayoutManager(layoutManager);

        GetStoryTask getStoryTask = new GetStoryTask();
        getStoryTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearActionMode();
            }
        });

    }

    private void clearActionMode() {
        isInActionMode = false;
        textViewToolbar.setVisibility(View.GONE);
        textViewToolbar.setText("0 item selected");
        imageButtonBack.setVisibility(View.GONE);
        counter = 0;
        selectionList.clear();
        for (ModelTest modelTest: arrStory) {
            modelTest.setCheckCheckBox(false);
        }
        toolbar.getMenu().clear();
        adapter.notifyDataSetChanged();
    }

    public void startSelection(int index){
        if(!isInActionMode){
            isInActionMode = true;
            selectionList.add(arrStory.get(index));
            counter++;
            updateToolbarText(counter);
            textViewToolbar.setVisibility(View.VISIBLE);
            imageButtonBack.setVisibility(View.VISIBLE);
            toolbar.inflateMenu(R.menu.menu_action_mode);
            position = index;

            adapter.notifyDataSetChanged();

        }
    }

    public void check(int index){
        if( arrStory.get(index).getCheckCheckBox() ){
            selectionList.add(arrStory.get(index));
            counter++;
            updateToolbarText(counter);
        }else {
            selectionList.remove(arrStory.get(index));
            counter--;
            updateToolbarText(counter);
        }
    }

    private void updateToolbarText(int counter) {
        if (counter == 0){
            textViewToolbar.setText("0 item selected");
        }
        if (counter == 1){
            textViewToolbar.setText("1 item selected");
        }else {
            textViewToolbar.setText( counter + " items selected");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_delete ) {

            if (selectionList.size() > 0) {
                new AlertDialog.Builder(this)
                        .setTitle("Confirm")
                        .setMessage("Delete "+selectionList.size()+" items ?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                for(ModelTest story: selectionList){
                                    arrStory.remove(story);
                                }
                                updateToolbarText(0);
                                clearActionMode();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create().show();
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLongClickItem(int position) {
        startSelection(position);
    }

    @Override
    public void onClickItem(int position) {
        check(position);
    }

    class GetStoryTask extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressBar;
        private boolean check = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = new ProgressDialog(Main2Activity.this);
            progressBar.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
                    RetrofitApi.getService().getStorys().enqueue(new Callback<List<ModelTest>>() {
                        @Override
                        public void onResponse(Call<List<ModelTest>> call, Response<List<ModelTest>> response) {
                            if (response.code() == 200) {
                                arrStory = response.body();
                            }
                            check = true;
                        }

                        @Override
                        public void onFailure(Call<List<ModelTest>> call, Throwable t) {
                            check = true;
                        }
                    });

            while (!check) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter = new StoryAdapter((ArrayList<ModelTest>) arrStory, Main2Activity.this, Main2Activity.this);
            rcvStory.setAdapter(adapter);
            progressBar.dismiss();
        }
    }


}
