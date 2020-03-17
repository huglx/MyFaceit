package com.isteel.myfaceit.utils;

import android.graphics.Rect;
import android.view.ViewTreeObserver;

import androidx.annotation.ContentView;

public class CommonUtil {
/*
    void ifKeyBoardShows() {
        final boolean[] isKeyboardShowing = {false};

// ContentView is the root view of the layout of this activity/fragment
        
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {

                        Rect r = new Rect();
                        contentView.getWindowVisibleDisplayFrame(r);
                        int screenHeight = contentView.getRootView().getHeight();

                        // r.bottom is the position above soft keypad or device button.
                        // if keypad is shown, the r.bottom is smaller than that before.
                        int keypadHeight = screenHeight - r.bottom;

                        if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                            // keyboard is opened
                            if (!isKeyboardShowing[0]) {
                                isKeyboardShowing[0] = true;
                            }
                        } else {
                            // keyboard is closed
                            if (isKeyboardShowing[0]) {
                                isKeyboardShowing[0] = false;
                            }
                        }
                    }
                });
    }*/
}
