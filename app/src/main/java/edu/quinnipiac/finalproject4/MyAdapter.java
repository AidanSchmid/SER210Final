package edu.quinnipiac.finalproject4;

// Code wholly taken from geeksforgeeks again. I'll probably replace this with the nice, crisp Fallscrier cardviews class later.

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter
        extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> data;
    Activity activity;

    public MyAdapter(Activity activity,
                     List<String> data)
    {
        this.data = data;
        this.activity = activity;
    }

    // This method is used to attach
    // custom layout to the recycler view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater LI = activity.getLayoutInflater();
        View vw = LI.inflate(R.layout.layout_recycler_item, null);
        return new ViewHolder(vw);
    }

    // This method is used to set the action
    // to the widgets of our custom layout.
    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position)
    {
        holder.topic_name
                .setText(data.get(position));
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    class ViewHolder
            extends RecyclerView.ViewHolder {
        TextView topic_name;
        public ViewHolder(View itemView)
        {
            super(itemView);
            this.topic_name
                    = itemView.findViewById(R.id.textView);
        }
    }
}