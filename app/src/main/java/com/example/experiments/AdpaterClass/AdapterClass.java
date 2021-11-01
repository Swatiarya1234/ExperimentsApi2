package com.example.experiments.AdpaterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.experiments.Generatepojoclasses.Root;
import com.example.experiments.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyAdapterclass> {

    private Context context;
    private boolean availableCource=false;
    List<Root.AvailableCategory> availableCategories;

    public AdapterClass(Context applicationContext, List<Root.AvailableCategory> availableCategory) {
         context = applicationContext;
         this.availableCategories = availableCategory;
    }

    @NonNull
    @Override
    public AdapterClass.MyAdapterclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.items_card,parent,false);
        return new MyAdapterclass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterclass holder, int position) {
             holder.textView.setText(availableCategories.get(position).getType());
              Picasso.get()
                .load(availableCategories.get(position).getImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return availableCategories.size();
    }
    public  static class MyAdapterclass extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView  textView;

        public MyAdapterclass(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            textView = itemView.findViewById(R.id.airconditoiner);

        }
    }

}
