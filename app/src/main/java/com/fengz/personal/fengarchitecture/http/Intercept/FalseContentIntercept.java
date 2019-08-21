package com.fengz.personal.fengarchitecture.http.Intercept;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 创建时间：2018/11/6
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：处理 Context:null 的特殊情况
 */
public class FalseContentIntercept implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        Headers header = response.headers();
        String type = header.get("Content-Type");
        if ("application/json;charset=UTF-8".equals(type)) {
            String result = response.body().string();
            try {
                JSONObject object = new JSONObject(result);
                String content = object.optString("body");
                if ("{}".equals(content)) {
                    result = result.replace(",\"body\":{}", "");
                }
                // 重新创建Response
                MediaType mediaType = response.body().contentType();
                return response.newBuilder()
                        .body(ResponseBody.create(mediaType, result))
                        .build();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return response;
    }
}
