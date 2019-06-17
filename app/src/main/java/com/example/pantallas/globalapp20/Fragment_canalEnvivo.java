package com.example.pantallas.globalapp20;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Fragment_canalEnvivo extends Fragment {

    private WebView miWebView;


    public Fragment_canalEnvivo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_fragment_canal_envivo, container, false);



        miWebView = view.findViewById(R.id.web_canal_via3tv);
        miWebView.getSettings().setJavaScriptEnabled(true);
        miWebView.setWebViewClient(new WebViewClient());

        miWebView.loadUrl("http://190.90.143.12/indexmovil.html");





       return view;
    }

}
