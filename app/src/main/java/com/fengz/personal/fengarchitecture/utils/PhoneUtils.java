package com.fengz.personal.fengarchitecture.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.TextUtils;

/**
 * 创建时间：2019/6/5
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：打电话工具类
 */
public class PhoneUtils {

    /**
     * 创建时间：2019/6/5
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：打电话
     */
    public static void callUser(Context context, String tel) {
        if (TextUtils.isEmpty(tel)) return;
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 创建时间：2019/6/5
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：适配华为等的￥符号只有一横的问题
     */
    public static String getYuan() {
        String yuan = Html.fromHtml("&yen").toString();
        return yuan;
    }
}
