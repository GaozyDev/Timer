package com.gzy.timer.ui.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gzy.timer.R;

import java.util.ArrayList;
import java.util.List;

public class TimerAdapter extends RecyclerView.Adapter<TimerAdapter.ViewHolder> {

    private Context mContext;

    private List<String> stringList = new ArrayList<>();

    public TimerAdapter(Context context) {
        this.mContext = context;
    }

    public void setStringList(List<String> list) {
        stringList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_timer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }
    }
}
