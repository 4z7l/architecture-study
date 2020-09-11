package org.techtown.mvvm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.techtown.mvvm.R
import org.techtown.mvvm.adapter.MemoAdapter
import org.techtown.mvvm.databinding.ActivityMainBinding
import org.techtown.mvvm.entity.Memo
import org.techtown.mvvm.viewmodel.MainViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init(){
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val memoAdapter = MemoAdapter(this)
        rv_memo_list.apply {
            adapter = memoAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }

        btn_add.setOnClickListener {
            val date = edt_date.text.toString()
            val contents = edt_contents.text.toString()
            viewModel.insertMemo(Memo(null, date, contents))
        }

        viewModel.memoList.observe(this, {
            memoAdapter.setList(it)
        })


    }

}