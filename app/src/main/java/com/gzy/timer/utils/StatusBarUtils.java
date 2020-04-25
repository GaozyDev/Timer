package com.gzy.timer.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 沉浸式状态栏工具
 */
public class StatusBarUtils {

    /**
     * 设置状态栏透明
     *
     * @param activity Activity
     */
    public static void setTransparent(Activity activity) {
        Window window = activity.getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * 设置状态栏文字颜色
     *
     * @param context 上下文，尽量使用Activity
     * @param isDark  true 深色 false 白色
     */
    public static void setTextColor(Context context, boolean isDark) {
        if (context instanceof Activity) {
            setTextColor(((Activity) context).getWindow(), isDark);
        }
    }

    /**
     * 设置状态栏文字颜色
     *
     * @param window 窗口，可用于Activity和全屏Dialog
     * @param isDark true 深色 false 白色
     */
    private static void setTextColor(Window window, boolean isDark) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            View decorView = window.getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            if (isDark) {
                decorView.setSystemUiVisibility(systemUiVisibility | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decorView.setSystemUiVisibility(systemUiVisibility & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        } else {
            if (RomUtil.isMiui()) {
                setMIUIDark(window, isDark);
            } else if (RomUtil.isFlyme()) {
                setFlymeDark(window, isDark);
            }
        }
    }

    /**
     * 设置MIUI系统状态栏文字颜色
     *
     * @param window 窗口，仅可用于Activity
     * @param isDark true 深色 false 白色
     */
    private static void setMIUIDark(Window window, boolean isDark) {
        try {
            Class<? extends Window> clazz = window.getClass();
            int darkModeFlag;
            @SuppressLint("PrivateApi")
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(window, isDark ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置Flyme系统状态栏文字颜色
     *
     * @param window Window
     * @param isDark true 深色 false 白色
     */
    private static void setFlymeDark(Window window, boolean isDark) {
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (isDark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}