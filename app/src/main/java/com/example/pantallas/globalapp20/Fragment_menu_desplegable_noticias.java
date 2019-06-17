package com.example.pantallas.globalapp20;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_menu_desplegable_noticias.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_menu_desplegable_noticias#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_menu_desplegable_noticias extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private WebView miWebView;
    ProgressBar progressBarCircular;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment_menu_desplegable_noticias() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_menu_desplegable_noticias.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_menu_desplegable_noticias newInstance(String param1, String param2) {
        Fragment_menu_desplegable_noticias fragment = new Fragment_menu_desplegable_noticias();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_menu_desplegable_noticias, container, false);


        new AsyncTask_load().execute();

        progressBarCircular = view.findViewById(R.id.ProgresBarcirculo);
        progressBarCircular.setProgress(0);

        miWebView = view.findViewById(R.id.Wv_noticias);
        miWebView.getSettings().setJavaScriptEnabled(true);
        miWebView.setWebViewClient(new WebViewClient());
        miWebView.loadUrl("https://www.elespectador.com/periodico");

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public class AsyncTask_load extends AsyncTask<Void, Integer, Void> {

        int progreso;


        @Override
        protected void onPreExecute() {
            Toast.makeText(getContext(), "onPreExecute", Toast.LENGTH_LONG).show();
            progreso = 0;
            progressBarCircular.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            while(progreso < 100){
                progreso++;
                publishProgress(progreso);
                SystemClock.sleep(50);
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {


            progressBarCircular.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getContext(), "onPostExecute", Toast.LENGTH_LONG).show();
            progressBarCircular.setVisibility(View.INVISIBLE);
        }


    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
