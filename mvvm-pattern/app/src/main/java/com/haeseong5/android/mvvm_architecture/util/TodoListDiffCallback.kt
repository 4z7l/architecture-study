package com.haeseong5.android.mvvm_architecture.util

import androidx.recyclerview.widget.DiffUtil
import com.haeseong5.android.mvvm_architecture.model.TodoModel

/**
 * 기존의 어댑터는 아이템이 갱신되면, 리스트 전체를 전달받고 notifyDataSetChanged() 호출하는 방식이다.
 * 이러한 방식은 아이템의 개수가 굉장히 많아질 경우, 성능 향상이 떨어질 수 있다.
 * 따라서 전체 리스트 중 업데이트된 항목만 골라서 업데이트 할 수 있는 DiffUtil을 사용하여 리스트의 성능을 향상시키고자 한다.
 */

//생성자로부터 oldList 와 newList 를 전달받아 두 리스트를 비교할 것이다.
class TodoListDiffCallback(val oldTodoModelList: List<TodoModel>, val newTodoModelList: List<TodoModel>):
    DiffUtil.Callback() {

    // 각각의 리스트[position]에 두 아이템이 동일한지 묻는 것
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTodoModelList[oldItemPosition].id == newTodoModelList[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldTodoModelList.size

    override fun getNewListSize(): Int = newTodoModelList.size

    /**
     * areItemsThewSame() 에서 두 아이템이 같으면 두 아이템의 값(내용물) 까지 같은지 체크
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newTodoModelList[newItemPosition] == oldTodoModelList[oldItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}