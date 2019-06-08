package com.trustpay.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.trustpay.R
import kotlinx.android.synthetic.main.row_operator.view.*

class OperatorAdapter: RecyclerView.Adapter<OperatorAdapter.OperatorHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): OperatorHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_operator, parent, false)
        return OperatorHolder(view)
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: OperatorHolder, position: Int) {
        if(position == 1){
            Picasso.get().load(R.drawable.icon_momo).into(holder.imageOperator)
        }
    }

     class OperatorHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageOperator = view.image_operator
    }
}