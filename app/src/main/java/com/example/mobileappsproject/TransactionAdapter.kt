package com.example.mobileappsproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappsproject.R

class TransactionAdapter(private val transactionList: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactionList[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val amountTextView: TextView = itemView.findViewById(R.id.tv_amount)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tv_description)

        fun bind(transaction: Transaction) {
            amountTextView.text = "${if (transaction.type == "income") "+" else "-"}${transaction.amount} lv"
            descriptionTextView.text = transaction.description

            // Color the amount based on type
            amountTextView.setTextColor(
                itemView.context.getColor(
                    if (transaction.type == "income") android.R.color.holo_green_dark
                    else android.R.color.holo_red_dark
                )
            )
        }
    }
}
