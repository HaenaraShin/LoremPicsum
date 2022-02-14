package dev.haenara.lorempicsum.util.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.core.Observable

fun RecyclerView.addScrollObservable(threshold: Int = 0): Observable<Int> {
    return Observable.create { observable ->
        addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = recyclerView.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                    if ((totalItemCount - visibleItemCount) <= (firstVisibleItem + threshold)) {
                        observable.onNext(totalItemCount)
                    }
                }
            }
        )
    }
}
