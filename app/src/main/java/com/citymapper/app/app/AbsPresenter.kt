package com.citymapper.app.app

abstract class AbsPresenter<V> {

    var mView: V? = null

    /**
     * attach the view to the presenter
     *
     * @param view activity or fragment to attach it to the presenter
     */
    fun attachView(view: V) {
        this.mView = view
    }

    /**
     * remove the view from the presenter
     */
    fun deAttachView() {
        this.mView = null
    }

    /**
     * check if the view is attached or not to the presenter
     */
    fun isAttached(): Boolean {
        return mView == null
    }

}