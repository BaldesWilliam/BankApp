package com.helpmeproductions.willus08.bankapp.view.activities.history;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.helpmeproductions.willus08.bankapp.R;
import com.helpmeproductions.willus08.bankapp.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<Transaction> transactions = new ArrayList<>();

    HistoryAdapter(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);

        String amountText =String.format( "$ %.2f", transaction.getAmount());
        holder.type.setText(transaction.getType());
        holder.description.setText(transaction.getDescription());
        holder.date.setText(transaction.getDate());

        if(transaction.getType().equals("Deposit")){
            holder.amount.setTextColor(Color.BLACK);
        }else {
            holder.amount.setTextColor(Color.RED);
        }
        holder.amount.setText(amountText);

    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView type;
        TextView amount;
        TextView description;
        ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.tvDate);
            type = itemView.findViewById(R.id.tvTransactionType);
            amount = itemView.findViewById(R.id.tvAmount);
            description = itemView.findViewById(R.id.tvDescription);
        }
    }
}
