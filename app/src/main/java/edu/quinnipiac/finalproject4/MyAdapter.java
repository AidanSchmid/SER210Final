package edu.quinnipiac.finalproject4;

// Lifted from Fallscrier, which in turn lifted from LS07.

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.mViewHolder> {
    private final LinkedList<String> mList;
    private LayoutInflater mInflater;

    public MyAdapter(Context context, LinkedList<String> dataList) {
        mInflater = LayoutInflater.from(context);
        this.mList = dataList;
    }
    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.fragment_results,
                parent, false);
        return new mViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        // Retrieve the data for that position, then...
        String mTextCurrent = mList.get(position);
        // Add the text to the card view's contained text view.
        ((TextView) holder.CardItemView.findViewById(R.id.textView)).setText(mTextCurrent);
    }

    @Override
    public int getItemCount() {
        return  mList.size();
    }

    class mViewHolder extends RecyclerView.ViewHolder {
        public CardView CardItemView;
        MyAdapter mAdapter;

        public mViewHolder(View itemView, MyAdapter adapter) {
            super(itemView);
            CardItemView = itemView.findViewById(R.id.card_in_view);
            this.mAdapter = adapter;
        }
    }
}
