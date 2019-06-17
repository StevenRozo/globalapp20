package com.example.pantallas.globalapp20;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;




public class Fragment_EnviarComprobanteWhatsapp extends Fragment {

    private String texto_URL;

    private WebView webView;

    public Fragment_EnviarComprobanteWhatsapp() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            texto_URL = getArguments().getString("valorUrl");



        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment__enviar_comprobante_whatsapp, container, false);

        webView=(WebView) view.findViewById(R.id.WebViewWhatsapp);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(texto_URL);


        return view;
    }

}
