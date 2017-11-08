package org.cwb.pi4androidapp.base;

/**
 * Created by valter.franco on 11/2/2017.
 * Base Presenter for MVP architecture
 */

public interface BasePresenter {
    /**
     * Ties to activity lifecycle
     */
    void onViewReady();

    /**
     * Ties to activity lifecycle
     */
    void start();

    /**
     * Ties to activity lifecycle
     */
    void stop();
}
