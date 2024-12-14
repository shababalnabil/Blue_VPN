package com.webihostapp.xprofreevpnapp.customads;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class AdvertiseDialog {

    private final Dialog dialog;
    private final WebView webView;
    private AdclosedListener adclosedListener;
    private boolean isAdLoaded = false;

    public void setAdclosedListener(AdclosedListener adclosedListener) {
        this.adclosedListener = adclosedListener;
    }

    public AdvertiseDialog(Context context) {
        // Initialize the dialog
        dialog = new Dialog(context);
        dialog.setCancelable(false); // Prevents closing with back button
        dialog.setCanceledOnTouchOutside(false); // Prevents closing by touching outside

        ImageView closeButton = new ImageView(context);
        closeButton.setVisibility(View.GONE);

        // Initialize the WebView
        webView = new WebView(context);
        webView.getSettings().setJavaScriptEnabled(true);


        WebSettings webSettings = this.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setAllowFileAccess(true);
        this.webView.clearFormData();
        webSettings.setSaveFormData(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDatabaseEnabled(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setBlockNetworkLoads(false);


        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress==100){
                    webView.setVisibility(View.VISIBLE);
                }
            }
        });
        webView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                isAdLoaded = true;
                closeButton.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("intent://")) {
                    try {
                        String modifiedUrl = url.replace("intent://", "https://");
                        view.loadUrl(modifiedUrl);
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return super.shouldOverrideUrlLoading(view, url);
            }


        }); // Prevents opening in browser

        webView.setVisibility(View.GONE);

        // Create a FrameLayout as the root layout
        FrameLayout rootLayout = new FrameLayout(context);
        rootLayout.setBackgroundColor(Color.BLACK);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        params.gravity = Gravity.CENTER; // Center the TextView

        TextView textView = new TextView(context);
        textView.setLayoutParams(params);
        textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
        textView.setTextColor(Color.WHITE);

        textView.setText("Loading....");
        rootLayout.addView(textView);

        // Add WebView to the root layout
        rootLayout.addView(webView);

        // Create a close button
        closeButton.setImageResource(android.R.drawable.ic_menu_close_clear_cancel); // Use a default close icon
        FrameLayout.LayoutParams closeParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        closeParams.gravity = Gravity.END | Gravity.TOP;
        closeParams.topMargin = (int) (16 * context.getResources().getDisplayMetrics().density); // Add margin
        closeParams.rightMargin = (int) (16 * context.getResources().getDisplayMetrics().density);
        closeButton.setLayoutParams(closeParams);

        // Set click listener to dismiss dialog

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAdLoaded){
                    dismiss();
                }
            }
        });

        // Add close button to the root layout
        rootLayout.addView(closeButton);

        // Set the root layout as dialog content
        dialog.setContentView(rootLayout);

        // Set dialog background transparent
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Ensure the dialog is fullscreen
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    // Load a URL in the WebView
    public void loadUrl(String url) {
        if (webView != null) {
            webView.loadUrl(url);
        }
    }

    // Show the dialog and load a URL
    public void show(String url) {
        loadUrl(url);
        dialog.show();
    }

    // Dismiss the dialog
    public void dismiss() {
        if (dialog.isShowing()) {
            dialog.dismiss();
            if (adclosedListener != null) {
                adclosedListener.onAdClosed();
            }
        }
    }
}

