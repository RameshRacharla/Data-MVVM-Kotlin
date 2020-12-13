package com.rameshracharla.datamvvmkotlin.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rameshracharla.datamvvmkotlin.MainActivity
import com.rameshracharla.datamvvmkotlin.R
import com.rameshracharla.datamvvmkotlin.databinding.ItemDataBinding
import com.rameshracharla.datamvvmkotlin.model.DataList
import com.rameshracharla.datamvvmkotlin.view.activity.UserDataActivity

public class MainActivityAdapter(mainActivity: MainActivity) :
    RecyclerView.Adapter<MainActivityAdapter.MyViewHolder>() {

    public var dataList: List<DataList>? = null
    var context: Context? = null

    init {
        dataList = ArrayList()
        context = mainActivity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding: ItemDataBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_data,
                parent,
                false
            )
        return MyViewHolder(binding)

    }


    override fun getItemCount(): Int {
        return dataList!!.size
    }

    fun addDataList(list: List<DataList>) {
        this.dataList = list
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.model = dataList!!.get(position)
        holder.binding.cardView.setOnClickListener {
            val intent = Intent(context, UserDataActivity::class.java)
            intent.putExtra("userData", dataList!!.get(position).id)
            context!!.startActivity(intent)
        }
    }

    class MyViewHolder(var binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.model = data as DataList?
        }
    }


}