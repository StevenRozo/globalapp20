package com.example.pantallas.globalapp20;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_programacion_canales extends Fragment {


    Spinner SpnCanales;
    private WebView miWebView;

    public Fragment_programacion_canales() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_programacion_canales, container, false);

        SpnCanales =(Spinner) view.findViewById(R.id.SpnGrillaCanales);

        CargarSpinner();
        miWebView = view.findViewById(R.id.Wv_main_programacion_canales);
        miWebView.getSettings().setJavaScriptEnabled(true);
        miWebView.setWebViewClient(new WebViewClient());


        return view;
    }


    private void CargarSpinner(){



        ArrayList<String> comboContratosList = new ArrayList<>();

        String canal01= "CANAL_CAPITAL";String canal22= "ID";String canal42= "TNT_SERIES";String canal62= "CRISTOVISION ";
        String canal02= "DISCOVERY";String canal23= "TLC";String canal43= "TCM";String canal63= "TELEAMIGA";
         String canal24= "DISCOVERY_SCIENCE";String canal44= "MULTI_PREMIER";String canal64= "ENLACE";

        String canal05= "CANAL_UNO";String canal25= "DISCOVERY_TURBO";String canal45= "AZTECA_CINEMA";String canal65= "3ABN";
        String canal06= "RCN";String canal26= "NATGEO";String canal46= "FX";String canal66= "CARACOL";String canal86= "TELEANTIOQUIA";
        String canal07= "CABLE_NOTICIAS";String canal27= "NATGEO_WILD";String canal47= "COMEDICENTRAL";String canal67= "CANAL_TRO";
        String canal08= "CARACOL";String canal28= "DISNEY_CH";String canal48= "FOXLIFE";String canal68= "TELEPACIFICO";
        String canal09= "SEÑAL_COLOMBIA";String canal29= "NAT_GEO_KIDS";String canal69= "TELECARIBE";
        String canal10= "CITY_TV";String canal30= "DISNEY_XD";String canal70= "TELECAFE";
        String canal11= "CANAL_13";String canal31= "CARTOON_NETWORK";String canal51= "AZTECA_MUNDO";
        String canal12= "ESPN";String canal32= "DISCOVERY_KIDS";String canal52= "AZTECA_CORAZON";
        String canal13= "ESPN2";String canal33= "TOONCAST";String canal53= "MTV";String canal73= "CANAL_INSTITUCIONAL";
        String canal14= "ESPN3";String canal34= "BABY_TV";String canal54= "RUMBA_TV";String canal74= "DESTINOS_TV";
        String canal15= "WIN_SPORTS";String canal35= "NICKELODEON";String canal55= "TELENOSTALGIA";String canal75= "ZOOM";
        String canal16= "FOX_SPORTS";String canal36= "BOOMERANG";String canal56= "TBS";String canal76= "MI_GENTE_TV";
        String canal17= "FOX_SPORTS2";String canal37= "TNT";String canal57= "CNN_EN_ESPAÑOL";String canal77= "DW_EN_ESPAÑOL";
        String canal18= "DISCOVERY_H&H";String canal38="FOX_CHANNEL";
        String canal19= "CINE_LATINO";String canal39= "THE_FILM_ZONE";String canal59= "FOXNEWS";
        String canal20= "ANIMAL_PLANET";String canal40= "PARAMOUNT_CHANNEL";String canal60= "TELESUR";String canal80= "ANTENA_3";
        String canal21= "DISCOVERY_CIVILIZATION";String canal41= "CINE_CANAL";String canal61= "EWTN";String canal81= "VIA3TV";



        comboContratosList.add("CANAL: ▼ ");

        comboContratosList.add(canal01); comboContratosList.add(canal20); comboContratosList.add(canal39); comboContratosList.add(canal77);
        comboContratosList.add(canal02); comboContratosList.add(canal21); comboContratosList.add(canal40); comboContratosList.add(canal59);
         comboContratosList.add(canal22); comboContratosList.add(canal41); comboContratosList.add(canal60); comboContratosList.add(canal86);
        comboContratosList.add(canal23); comboContratosList.add(canal42); comboContratosList.add(canal61); comboContratosList.add(canal80);
        comboContratosList.add(canal05); comboContratosList.add(canal24); comboContratosList.add(canal43); comboContratosList.add(canal62);
        comboContratosList.add(canal06); comboContratosList.add(canal25); comboContratosList.add(canal44); comboContratosList.add(canal63);
        comboContratosList.add(canal07); comboContratosList.add(canal26); comboContratosList.add(canal45); comboContratosList.add(canal64);
        comboContratosList.add(canal08); comboContratosList.add(canal27); comboContratosList.add(canal46); comboContratosList.add(canal65);
        comboContratosList.add(canal09); comboContratosList.add(canal28); comboContratosList.add(canal47); comboContratosList.add(canal66);
        comboContratosList.add(canal10); comboContratosList.add(canal29); comboContratosList.add(canal48); comboContratosList.add(canal67);
        comboContratosList.add(canal11); comboContratosList.add(canal30);  comboContratosList.add(canal68);comboContratosList.add(canal70);
        comboContratosList.add(canal12); comboContratosList.add(canal31); comboContratosList.add(canal69);comboContratosList.add(canal73);
        comboContratosList.add(canal13); comboContratosList.add(canal32); comboContratosList.add(canal51); comboContratosList.add(canal74);
        comboContratosList.add(canal14); comboContratosList.add(canal33); comboContratosList.add(canal52); comboContratosList.add(canal75);
        comboContratosList.add(canal15); comboContratosList.add(canal34); comboContratosList.add(canal53);comboContratosList.add(canal76);
        comboContratosList.add(canal16); comboContratosList.add(canal35); comboContratosList.add(canal54);comboContratosList.add(canal81);
        comboContratosList.add(canal17); comboContratosList.add(canal36); comboContratosList.add(canal55);
        comboContratosList.add(canal18); comboContratosList.add(canal37); comboContratosList.add(canal56);
        comboContratosList.add(canal19); comboContratosList.add(canal38); comboContratosList.add(canal57);






        ArrayAdapter<CharSequence> adapter= new ArrayAdapter(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,comboContratosList);




        SpnCanales.setAdapter(adapter);

        SpnCanales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               // Toast.makeText(getContext(), ""+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

              String Canal_Seleccionado= parent.getItemAtPosition(position).toString();

        switch (Canal_Seleccionado){

            case "CANAL_CAPITAL" :

                Toast.makeText(getContext(), "CANAL CAPITAL", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/canal-capital");

                break;

            case "CANAL_UNO" :

                Toast.makeText(getContext(), "CANAL UNO", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://canal1.com.co/programas/");

                break;
            case "DISCOVERY" :

                Toast.makeText(getContext(), "Discovery", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/discovery-channel");

                break;
            case "RCN":
                Toast.makeText(getContext(), "rcn", Toast.LENGTH_SHORT).show();

                miWebView.loadUrl("https://mi.tv/co/canales/rcn");
                break;
            case "CABLE_NOTICIAS":
                Toast.makeText(getContext(), "CABLE NOTICIAS", Toast.LENGTH_SHORT).show();

                miWebView.loadUrl("https://mi.tv/co/canales/cablenoticias");
                break;
            case "CARACOL":
                Toast.makeText(getContext(), "CARACOL", Toast.LENGTH_SHORT).show();
                miWebView.loadUrl("https://www.caracoltv.com/programacion");
                break;

                case "TNT_SERIES" :

                Toast.makeText(getContext(), "TNT SERIES", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/tnt-series");

                break;
            case "SEÑAL_COLOMBIA":
                Toast.makeText(getContext(), "SEÑAL COLOMBIA", Toast.LENGTH_SHORT).show();
                miWebView.loadUrl("https://www.senalcolombia.tv/parrilla");
                break;


            case "ESPN":
                Toast.makeText(getContext(), "spn", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/espn");
                break;

            case "ESPN2":
                Toast.makeText(getContext(), "spn 2", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/espn-2");
                break;
            case "ESPN3":
                Toast.makeText(getContext(), "spn 3", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/espn-3");
                break;

            case "CITY_TV":
                Toast.makeText(getContext(), "CITY TV", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/citytv");
                break;

            case "CANAL_13":
                Toast.makeText(getContext(), "CANAL 13", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/canal-tr3ce");
                break;


            case "WIN_SPORTS":
                Toast.makeText(getContext(), "WIN SPORTS", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/win-sports");
                break;


            case "FOX_SPORTS":
                Toast.makeText(getContext(), "FOX_SPORTS", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/fox-sports");
                break;


            case "FOX_SPORTS2":
                Toast.makeText(getContext(), "FOX SPORTS 2", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/fox-sports-2");
                break;


            case "DISCOVERY_H&H":
                Toast.makeText(getContext(), "DISCOVERY H&H", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/discovery-home-and-health");
                break;


            case "CINE_LATINO":
                Toast.makeText(getContext(), "CINE LATINO", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/cinelatino");
                break;


            case "ANIMAL_PLANET":
                Toast.makeText(getContext(), "ANIMAL PLANET", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/animal-planet");
                break;


            case "DISCOVERY_CIVILIZATION":
                Toast.makeText(getContext(), "DISCOVERY CIVILIZATION", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/discovery-civilization");
                break;


            case "ID":
                Toast.makeText(getContext(), "ID", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/discovery-id-hd");
                break;


            case "TLC":
                Toast.makeText(getContext(), "TLC", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/discovery-tlc");
                break;


            case "DISCOVERY_SCIENCE":
                Toast.makeText(getContext(), "DISCOVERY SCIENCE", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/discovery-science");
                break;


            case "DISCOVERY_TURBO":
                Toast.makeText(getContext(), "DISCOVERY TURBO", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/discovery-turbo");
                break;


            case "NATGEO":
                Toast.makeText(getContext(), "NATGEO", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/national-geographic");
                break;


            case "NATGEO_WILD":
                Toast.makeText(getContext(), "NATGEO WILD", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/nat-geo-wild");
                break;
            case "DISNEY_CH":
                Toast.makeText(getContext(), "DISNEY CH", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/disney-multicountry");
                break;


            case "NAT_GEO_KIDS":
                Toast.makeText(getContext(), "NAT GEO KIDS", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/pe/canales/nat-geo-kids");
                break;


            case "DISNEY_XD":
                Toast.makeText(getContext(), "DISNEY XD", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/disney-xd-multicountry");
                break;
            case "CARTOON_NETWORK":
                Toast.makeText(getContext(), "CARTOON NETWORK", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/cartoon-network");
                break;
            case "DISCOVERY_KIDS":
                Toast.makeText(getContext(), "DISCOVERY KIDS", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/discovery-kids");
                break;
            case "TOONCAST":
                Toast.makeText(getContext(), "TOONCAST", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/tooncast");
                break;
            case "BABY_TV":
                Toast.makeText(getContext(), "BABY TV", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/cl/canales/babytv");
                break;
            case "NICKELODEON":
                Toast.makeText(getContext(), "NICKELODEON", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/nickelodeon");
                break;
            case "BOOMERANG":
                Toast.makeText(getContext(), "BOOMERANG", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/boomerang-cartoon");
                break;
            case "FOX_CHANNEL":
                Toast.makeText(getContext(), "FOX CHANNEL", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/fox");
                break;
            case "THE_FILM_ZONE":
                Toast.makeText(getContext(), "THE FILM ZONE", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/pe/canales/film-zone");
                break;
            case "PARAMOUNT_CHANNEL":
                Toast.makeText(getContext(), "PARAMOUNT CHANNEL", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/paramount-channel");
                break;
            case "CINE_CANAL":
                Toast.makeText(getContext(), "CINE CANAL", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/cinecanal");
                break;
            case "TCM":
                Toast.makeText(getContext(), "TCM", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/tcm");
                break;
            case "MULTI_PREMIER":
                Toast.makeText(getContext(), "MULTI PREMIER", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/multipremier");
                break;
            case "AZTECA_CINEMA":
                Toast.makeText(getContext(), "AZTECA CINEMA", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/cl/canales/az-cinema");
                break;
            case "COMEDICENTRAL":
                Toast.makeText(getContext(), "COMEDICENTRAL", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/comedy-central");
                break;
            case "FOXLIFE":
                Toast.makeText(getContext(), "FOXLIFE", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/fox-life");
                break;
            case "AZTECA_MUNDO":
                Toast.makeText(getContext(), "AZTECA MUNDO", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/az-mundo-hd");
                break;
            case "AZTECA_CORAZON":
                Toast.makeText(getContext(), "AZTECA CORAZON", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/az-corazon");
                break;
            case "MTV":
                Toast.makeText(getContext(), "MTV", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/m-tv");
                break;

            case "RUMBA_TV":
                Toast.makeText(getContext(), "RUMBA TV", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/rumba-tv");
                break;
            case "TELENOSTALGIA":
                Toast.makeText(getContext(), "TELENOSTALGIA", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/tele-nostalgia");
                break;
            case "TBS":
                Toast.makeText(getContext(), "TBS", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/tbs-veryfunny-pan");
                break;
            case "CNN_EN_ESPAÑOL":
                Toast.makeText(getContext(), "CNN EN ESPAÑOL", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/cnn-en-espanol");
                break;
            case "FOXNEWS":
                Toast.makeText(getContext(), "FOXNEWS", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/fox-news-internacional");
                break;
            case "TELESUR":
                Toast.makeText(getContext(), "TELESUR", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/ar/canales/telesur");
                break;
            case "EWTN":
                Toast.makeText(getContext(), "EWTN", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/ewtn");
                break;
            case "CRISTOVISION":
                Toast.makeText(getContext(), "CRISTOVISION", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/cristo-vision");
                break;
            case "ENLACE":
                Toast.makeText(getContext(), "ENLACE", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/enlace");
                break;
            case "3ABN":
                Toast.makeText(getContext(), "3ABN", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/hn/canales/3abn-latino");
                break;
            case "TELEANTIOQUIA":
                Toast.makeText(getContext(), "TELEANTIOQUIA", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/teleantioquia");
                break;
            case "CANAL_TRO":
                Toast.makeText(getContext(), "CANAL_TRO", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/tro");
                break;
            case "TELEPACIFICO":
                Toast.makeText(getContext(), "TELEPACIFICO", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/telepacifico");
                break;

            case "TELECARIBE":
                Toast.makeText(getContext(), "TELECARIBE", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/telecaribe");
                break;
            case "TELECAFE":
                Toast.makeText(getContext(), "TELECAFE", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/telecafe");
                break;
            case "CANAL_INSTITUCIONAL":
                Toast.makeText(getContext(), "CANAL INSTITUCIONAL", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/senal-institucional-rtvc");
                break;
            case "DESTINOS_TV":
                Toast.makeText(getContext(), "DESTINOS TV", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/pe/canales/destinos-tv");
                break;
            case "ZOOM":
                Toast.makeText(getContext(), "ZOOM", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/zoom");
                break;
            case "MI_GENTE_TV":
                Toast.makeText(getContext(), "MI GENTE TV", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/mi-gente");
                break;
            case "DW_EN_ESPAÑOL":
                Toast.makeText(getContext(), "DW EN ESPAÑOL", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/deutsche-welle-amerika");
                break;
            case "ANTENA_3":
                Toast.makeText(getContext(), "ANTENA 3", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/antena3");
                break;
            case "TELEAMIGA":
                Toast.makeText(getContext(), "TELEAMIGA", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/teleamiga");
                break;
            case "FX":
                Toast.makeText(getContext(), "FX", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/fx");
                break;

            case "TNT":
                Toast.makeText(getContext(), "TNT", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("https://mi.tv/co/canales/tnt");
                break;

            case "VIA3TV":
                Toast.makeText(getContext(), "VIA3TV", Toast.LENGTH_SHORT).show();


                miWebView.loadUrl("http://programacioncanalvia3tv.blogspot.com/");
                break;







        }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
