package com.isteel.myfaceit.ui.base;

import io.reactivex.disposables.Disposable;

/**
 * @author Artur Vasilov
 */
public interface LoadingView {
    void hideLoading();

    void showLoading(Disposable disposable);
}