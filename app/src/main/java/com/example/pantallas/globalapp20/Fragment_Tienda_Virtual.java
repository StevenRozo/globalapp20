package com.example.pantallas.globalapp20;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Fragment_Tienda_Virtual extends Fragment {
    private WebView miWebView;


    public Fragment_Tienda_Virtual() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_fragment__tienda__virtual, container, false);

        miWebView = view.findViewById(R.id.Wv_main_tienda_virtual);
        miWebView.getSettings().setJavaScriptEnabled(true);
        miWebView.setWebViewClient(new WebViewClient());
        miWebView.loadUrl("http://www.globalplay.tv/?post_type=product");

        return view;
    }


}
