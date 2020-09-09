package com.haeseong5.android.githubwithrx.src.kotlin.rx

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.Disposable

/**
 * 메인액티비티에서는 액티비티가 활성상태(start~stop)일 때만 데이터베이스에 저장된 저장소 조회 기록을 표시할 것임.
 * start 가 호출됐을 때는 디스포저블로부터 이벤트를 받기 시작하고
 * stop 이 호출되면 이벤트 받는 것을 중단
 */
class AutoActivatedDisposable (
    private val lifecycleOwner: LifecycleOwner,
    private val func: ()->Disposable
) : LifecycleObserver {
    private var disposable: Disposable? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activate() {
        //disposable로 부터 event 받기 시작
        disposable = func.invoke()
    }

     @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
     fun deactivate(){
         //이벤트 받는 것 중단
         disposable?.dispose()
     }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detachSelf() {
        //생명주기 이벤트를 더이상 받지 않도록 옵저버에서 제거
        lifecycleOwner.lifecycle.removeObserver(this)
    }

}