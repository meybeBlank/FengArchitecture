package com.fengz.personal.fengarchitecture.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.fengz.personal.fengarchitecture.common.ProgressDialog;

/**
 * 创建时间：2019/3/13
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：Dialog统一管理类
 */
public class DialogManager {
    private DialogManager() {
    }

    /**
     * 创建时间：2019/3/13
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：创建提示框提示框
     *
     * @param info        展示信息
     * @param title       标题
     * @param negativeTxt 左侧按钮文案
     * @param positiveTxt 右侧按钮文案
     * @param negListener 左侧事件
     * @param posListener 右侧事件
     */
    public static AlertDialog createAlertDialog(Context context, String title, String info,
                                                String positiveTxt, String negativeTxt,
                                                DialogInterface.OnClickListener posListener,
                                                DialogInterface.OnClickListener negListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(info);
        builder.setCancelable(false);
        builder.setPositiveButton(positiveTxt, posListener);
        builder.setNegativeButton(negativeTxt, negListener);
        return builder.create();
    }

    /**
     * 创建时间：2019/3/13
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：进度条展示弹框
     */
    public static ProgressDialog showProgressDialog(Context context, String title,
                                                    final DialogInterface.OnClickListener nageListener) {
        return new ProgressDialog(context,title,nageListener);
    }
}
