package com.dhamodharan.GRcoconut.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dhamodharan.GRcoconut.R;
import com.dhamodharan.GRcoconut.dbconnection.Task;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.TasksViewHolder> {

    private Context mCtx;
    private List<Task> taskList;
    AdapterCallback callback;

    public NotesAdapter(List<Task> taskList,Context mCtx, AdapterCallback callback){
        this.mCtx = mCtx;
        this.taskList = taskList;
        this.callback=callback;

    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.notes, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Task t = taskList.get(position);


        holder.textCoconutweight.setText(t.getCoconutweight());
        holder.textCoconutprice.setText(t.getCoconutprice());
        holder.texttotalcoconut.setText(t.getTotalcoconut());

        holder.textDate.setText(t.getDate());
        holder.textNotes.setText(t.getNotes());
        holder.textWaste.setText(t.getWaste());
        holder.textTotalamount.setText(t.getTotalamount());
        holder.textPercoconut.setText(t.getPercoconut());
        holder.textWeight.setText(t.getWaste());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textDate,textNotes,textWaste,textTotalamount,textPercoconut,textWeight,textCoconutweight,textCoconutprice,texttotalcoconut;

        public TasksViewHolder(View itemView) {
            super(itemView);

            textDate = itemView.findViewById(R.id.textDate);
            textNotes = itemView.findViewById(R.id.textNotes);
            textWaste = itemView.findViewById(R.id.textWaste);
            textTotalamount = itemView.findViewById(R.id.textTotalamount);
            textPercoconut = itemView.findViewById(R.id.textPercoconut);
            textWeight = itemView.findViewById(R.id.textWeight);
            textCoconutweight = itemView.findViewById(R.id.textCoconutweight);
            textCoconutprice = itemView.findViewById(R.id.textCoconutprice);
            texttotalcoconut = itemView.findViewById(R.id.texttotalcoconut);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Task task = taskList.get(getAdapterPosition());
            callback.onItemClicked(task);
        }


    }


    public interface AdapterCallback{
        void onItemClicked(Task value);
    }

}