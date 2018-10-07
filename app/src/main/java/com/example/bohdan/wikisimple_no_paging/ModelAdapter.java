package com.example.bohdan.wikisimple_no_paging;

import android.arch.paging.AsyncPagedListDiffer;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ModelAdapter extends PagedListAdapter<Result, ModelAdapter.ModelViewHolder> {

    private final AsyncPagedListDiffer<Result> mDiffer
            = new AsyncPagedListDiffer(this, DIFF_CALLBACK);

    @Override
    public void submitList(PagedList<Result> pagedList) {
        //super.submitList(pagedList);
        mDiffer.submitList(pagedList);
    }

    public List<Result> model;
    public Context context_;

    public ModelAdapter() {
        super(DIFF_CALLBACK);
        //this.context_ = context;

    }

    @Override
    public int getItemCount() {
        if (mDiffer == null)
            return 0;
        return mDiffer.getItemCount();
    }

    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom,parent,false);
        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder holder, int position) {
        //.getItem(position).
        //Model  model_ = model.get(position);
        if(mDiffer!=null){
            holder.textView.setText(mDiffer.getItem(position).getPillarName());
            holder.textViewTwo.setText(mDiffer.getItem(position).getId());
            Picasso.get().load(mDiffer.getItem(position).getFields().getThumbnail()).into(holder.imageView);
        }


        /*if (model!=null){
            holder.textView.setText(model.get(position).getPillarName());
            holder.textViewTwo.setText(model.get(position).getId());
            Picasso.get().load(model.get(position).getFields().getThumbnail()).into(holder.imageView);
        }*/
    }


    public class ModelViewHolder extends RecyclerView.ViewHolder{

        TextView textView, textViewTwo;
        ImageView imageView;

        public ModelViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView_id);
            textViewTwo = itemView.findViewById(R.id.textView2_description);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }


    public static final DiffUtil.ItemCallback<Result> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Result>() {
                @Override
                public boolean areItemsTheSame(Result oldItem, Result newItem) {
                    //return oldItem.getResponse().getResults() getId()==newItem.getId();
                    return oldItem.getId()==newItem.getId();
                    //return false;
                }

                @Override
                public boolean areContentsTheSame(Result oldItem, Result newItem) {
                    return oldItem.equals(newItem);
                    // return false;
                }
            };
}
