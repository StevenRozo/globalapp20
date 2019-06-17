package com.example.pantallas.globalapp20;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_zona_cobertura extends Fragment {
    private WebView miWebView;

    public Fragment_zona_cobertura() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_fragment_zona_cobertura, container, false);

        miWebView = view.findViewById(R.id.Wv_main_zona_cobertura);
        miWebView.getSettings().setJavaScriptEnabled(true);
        miWebView.setWebViewClient(new WebViewClient());
        miWebView.loadUrl("http://www.globalplay.tv/?page_id=397");

       return view;
    }

}
