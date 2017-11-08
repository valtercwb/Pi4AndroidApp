package org.cwb.pi4androidapp.base;

import android.app.Activity;

/**
 * Created by valter.franco on 11/2/2017.
 * BaseView
 */

public interface BaseView<T> {
    /**
     * Sets this views presenter
     * @param presenter - presenter to be used
     */
    void setPresenter(T presenter);

    /**
     * Gets the activity
     * @return - activity
     */
    Activity getActivity();
}
