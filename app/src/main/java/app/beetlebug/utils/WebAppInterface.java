package app.beetlebug.utils;

import android.content.Context;
import android.webkit.JavascriptInterface;

import app.beetlebug.ctf.WebViewXSSActivity;

public class WebAppInterface {
    Context mContext;
    public WebAppInterface(Context c) {
        this.mContext = c;
    }

    @JavascriptInterface
    public String getUserToken() {
        return WebViewXSSActivity.getUserToken();
    }
}