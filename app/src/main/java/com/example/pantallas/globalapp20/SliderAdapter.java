package com.example.pantallas.globalapp20;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){


        this.context = context;

    }


    //arrays

    public int[] slide_images = {
            R.drawable.slidersietecorel,
            R.drawable.slidercincocorel,
            R.drawable.sliderseiscorel

    };

    // Array para añadir los campos de texto del slider
    public String [] slide_headings ={
            "TELEVISIÓN",
            "INTERNET",
            "Y MÁS"
    };

    public String[] slide_descs ={
            "Disfruta más de 70 canales en alta definición",
            "Internet de alta velocidad",
            "Estamos de tu lado brindandote beneficios al alcance de tus manos"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject( View view, Object o) {
        return view == (LinearLayoutCompat) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView slideImageView = (ImageView)view.findViewById(R.id.slide_image);
        TextView slideHeading= (TextView) view.findViewById(R.id.slide_titulo);
        TextView slideDescripcion= (TextView) view.findViewById(R.id.slide_description);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescripcion.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem( ViewGroup container, int position,Object object) {
        container.removeView((LinearLayoutCompat)object);
    }
}
