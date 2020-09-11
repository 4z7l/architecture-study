package org.techtown.mvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.mvvm.databinding.MemoViewBinding
import org.techtown.mvvm.entity.Memo

class MemoAdapter(private val context: Context) : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {

    private var memoList: List<Memo>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: MemoViewBinding = MemoViewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(memoList!![position])
    }

    override fun getItemCount(): Int {
        return if(memoList != null) memoList!!.size else 0
    }

    fun setList(list: List<Memo>){
        this.memoList = list
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: MemoViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindView(memo: Memo){
            binding.memo = memo
        }
    }
}