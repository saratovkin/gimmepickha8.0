package com.example.bramaceo.gimmepikcha;


import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Item> mElements;

    public RecyclerViewAdapter(List<Item> elements) {
        this.mElements = elements;
    }


    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.MyViewHolder holder, int position) {
        final Item element = mElements.get(position);

        ImageView image = holder.image;
        TextView textView = holder.textView;

        textView.setText(element.getName());
        Picasso.with(holder.getContext()).load((element.getUrl())).into(image);

        holder.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), view.getContext().getString(R.string.nothing), Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return mElements.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView textView;

        private View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            image = itemView.findViewById(R.id.item_image);
            textView = itemView.findViewById(R.id.item_title);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
        }

        public Context getContext() {
            return itemView.getContext();
        }
    }
}
