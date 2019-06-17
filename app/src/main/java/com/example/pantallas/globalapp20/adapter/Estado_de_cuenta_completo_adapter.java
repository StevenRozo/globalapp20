package com.example.pantallas.globalapp20.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pantallas.globalapp20.R;
import com.example.pantallas.globalapp20.entidades.Estado_de_cuenta_completo;

import java.util.List;

public class Estado_de_cuenta_completo_adapter extends RecyclerView.Adapter<Estado_de_cuenta_completo_adapter.UsuariosHolder>{

    List<Estado_de_cuenta_completo> listaitems;

    public Estado_de_cuenta_completo_adapter(List<Estado_de_cuenta_completo> listaitems, Context context) {
        this.listaitems = listaitems;
    }

    @Override
    public UsuariosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.estado_de_cuenta_completo_vista_adaptada,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new UsuariosHolder(vista);
    }

    @Override
    public void onBindViewHolder(UsuariosHolder holder, int position) {
        holder.idFechaTransac.setText(listaitems.get(position).getEst_cta_fecha_transacc());
        holder.idSaldAnterior.setText(listaitems.get(position).getEst_cta_saldo_anterior());
        holder.idComprobante.setText(listaitems.get(position).getEst_cta_no_comprobante());
        holder.idCargos.setText(listaitems.get(position).getEst_cta_haber());
        holder.idConcepto.setText(listaitems.get(position).getCon_tran_nombre());
        holder.idPagos.setText(listaitems.get(position).getEst_cta_debe());
        holder.idObservacion.setText(listaitems.get(position).getEst_cta_observacion());
        holder.idSaldoFinal.setText(listaitems.get(position).getEst_cta_saldo_actual());
    }

    @Override
    public int getItemCount() {
        return listaitems.size();
    }

    public class UsuariosHolder extends RecyclerView.ViewHolder{

        TextView idFechaTransac,idSaldAnterior,idComprobante,idCargos,idConcepto,idPagos,idObservacion,idSaldoFinal;

        public UsuariosHolder(View itemView) {
            super(itemView);
            idFechaTransac= (TextView) itemView.findViewById(R.id.idFechaTransac);
            idSaldAnterior= (TextView) itemView.findViewById(R.id.idSaldAnterior);
            idComprobante= (TextView) itemView.findViewById(R.id.idComprobante);
            idCargos= (TextView) itemView.findViewById(R.id.idCargos);
            idConcepto= (TextView) itemView.findViewById(R.id.idConcepto);
            idPagos= (TextView) itemView.findViewById(R.id.idPagos);
            idObservacion= (TextView) itemView.findViewById(R.id.idObservacion);
            idSaldoFinal= (TextView) itemView.findViewById(R.id.idSaldoFinal);


        }
    }
}
