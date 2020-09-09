package com.haeseong5.android.githubwithrx.src.kotlin.rx

import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * 이전에는 액티비티의 onStop() 에서 disposables.clear() 를 호출하여 디스포저블 객체들을 해제하도록 구현하였다.
 * 해당 클래스는 LifecycleObserver 인터페이스를 구현하여 생명주기에 따라 디스포저블을 자동으로 해제준다.
 */
class AutoClearedDisposable (
    //생명주기를 참조할 액티비티
    private val lifecycleOwner: AppCompatActivity,
    private val alwaysClearOnStop: Boolean = true,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable())
    : LifecycleObserver {

    /**
     * lifecycleOwner.lifecycle을 이용하여 참조하고 있는 컴포넌트의 lifeCycle 객체제 접근합니다.
     * lifecycle.currentState를 사용하여  상태정보인 LifeCycle.State에 접근합니다.
     * lifeCycle.State.isAtLeast() 함수를 사용하여
     * 현재상태가 특정 상태의 이후 상태인지 여부를 반환합니다.
     * 코틀린 표준 라이브러리에서 제공하는 check() 함수로
     * Lifecycle.State.isAtLeast() 함수의 반환 값이 참인지 확인하며,
     * 만약 참이 아닌 경우 예외를 발생시킨다.
     */
    fun addDisposable(disposable: Disposable) {
         check(lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED))
        //check() 검증 절차를 통과한 경우에만 디스포저블을 추가
        compositeDisposable.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun cleanUp(){
        //액티비티가 종료되지 않는 시점에는 디스포저블을 해제하지 않는다.
        d("Lifecycle", "onStop: cleanUp")
        if(!alwaysClearOnStop && !lifecycleOwner.isFinishing) {
            return
        }
        // 관리하는 디스포저블을 해제합니다.
        compositeDisposable.clear()
    }


    //onDestroy() 가 호출되면, detachSelf() 함수를 호출한다.
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detachSelf() {
        d("Lifecycle", "onDestroy: detachSelf")
        compositeDisposable.clear()
        // 더이상 액티비티의 생명주기 이벤트를 받지 않도록 액티비티 생명주기 옵저버에서 제거1
        lifecycleOwner.lifecycle.removeObserver(this)
    }

}