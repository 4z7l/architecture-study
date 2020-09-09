package com.haeseong5.android.mvvm_architecture.view

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.haeseong5.android.mvvm_architecture.R
import kotlinx.android.synthetic.main.dialog_todo.*

class TodoDialog(context: Context){
    private val dialog = Dialog(context)
    private lateinit var clickListener: TodoDialogClickedListener //다이어로그 내부 버튼 클릭리스너 인터페이스 선언

    fun start() {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dialog.setContentView(R.layout.dialog_todo)     //다이얼로그에 사용할 xml 파일을 불러옴
        dialog.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함


        dialog.dialog_todo_bt_positive.setOnClickListener {
                clickListener.onPositiveBtnClicked(dialog.dialog_todo_et_title.text.toString(), dialog.dialog_todo_et_description.text.toString())
            dialog.dismiss()
        }

        dialog.dialog_todo_bt_negative.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    fun setOnClickedListener(listener: (String, String) -> Unit) {
        this.clickListener = object: TodoDialogClickedListener{
            override fun onPositiveBtnClicked(title: String, description: String) {
                listener(title, description) // 액티비티에서 인자로 값 받아서 사용가능
            }

            override fun onNegativeBtnClicked() {
                TODO("Not yet implemented")
            } //인터페이스를 구현한 익명 객체를 clickListener 에 할당

        }
    }

    //클릭 리스너 인터페이스
    interface TodoDialogClickedListener {
        fun onPositiveBtnClicked(title: String, description: String)
        fun onNegativeBtnClicked()
    }

}

