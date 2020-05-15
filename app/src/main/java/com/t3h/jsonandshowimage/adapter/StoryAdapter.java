package com.t3h.jsonandshowimage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3h.jsonandshowimage.Main2Activity;
import com.t3h.jsonandshowimage.ModelTest;
import com.t3h.jsonandshowimage.OnLongClickItem;
import com.t3h.jsonandshowimage.R;

import java.util.ArrayList;


public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    private static ArrayList<ModelTest> arrStory;
    private Context context;
    private OnLongClickItem onLongClick;
    //private int status=0;


    public StoryAdapter(ArrayList<ModelTest> arrStory, Context context, OnLongClickItem onLongClick) {
        this.arrStory = arrStory;
        this.context = context;
        this.onLongClick = onLongClick;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_story, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StoryAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load(arrStory.get(position).getImage()).into(holder.imgAvatar);
        holder.tvName.setText(arrStory.get(position).getName());
        holder.tvAuthor.setText(arrStory.get(position).getAuthor());


        if ( Main2Activity.isInActionMode ) {
            Amin amin = new Amin(100, holder.linearLayout);
            amin.setDuration(300);
            holder.linearLayout.setAnimation(amin);

        }else{
            Amin amin = new Amin(0, holder.linearLayout);
            amin.setDuration(300);
            holder.linearLayout.setAnimation(amin);
        }

        if (arrStory.get(position).getCheckCheckBox()) {
            holder.checkBox.setChecked(true);
        }else {
            holder.checkBox.setChecked(false);
        }
//
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                arrStory.get(position).setCheckCheckBox(true);
                onLongClick.onLongClickItem(position);
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrStory.get(position).getCheckCheckBox()){
                    arrStory.get(position).setCheckCheckBox(false);
                    notifyItemChanged(position);
                }else {
                    arrStory.get(position).setCheckCheckBox(true);
                    notifyItemChanged(position);
                }

                onLongClick.onClickItem(position);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView tvName, tvAuthor;
        View mView;
        LinearLayout linearLayout;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            linearLayout = itemView.findViewById(R.id.lnLayout_item);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            checkBox = itemView.findViewById(R.id.cb_check);
        }

    }

    @Override
    public int getItemCount() {
        return arrStory.size();
    }


    class Amin extends Animation {
        private int width, startWidth;
        private View view;

        public Amin(int width, View view) {
            this.view = view;
            this.width = width;
            this.startWidth = view.getWidth();
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            int newWith = startWidth + (int) ((width - startWidth) * interpolatedTime);
            view.getLayoutParams().width = newWith;
            view.requestLayout();

            super.applyTransformation(interpolatedTime, t);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }
}
