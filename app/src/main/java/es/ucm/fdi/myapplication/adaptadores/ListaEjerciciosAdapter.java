package es.ucm.fdi.myapplication.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import es.ucm.fdi.myapplication.AmpliarActivity;
import es.ucm.fdi.myapplication.Ejercicio;
import es.ucm.fdi.myapplication.R;
import es.ucm.fdi.myapplication.VerActivity;

public class ListaEjerciciosAdapter extends RecyclerView.Adapter<ListaEjerciciosAdapter.EjercicioViewHolder> {

    ArrayList<Ejercicio> listaEjercicios;

    public ListaEjerciciosAdapter(ArrayList<Ejercicio> listaEjercicios){
        this.listaEjercicios = listaEjercicios;
    }
    @NonNull
    @NotNull
    @Override
    public EjercicioViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_ejercicio,null,false);
        return new EjercicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull EjercicioViewHolder holder, int position) {
        holder.viewEjercicio.setText(listaEjercicios.get(position).getNombreEjercicio());
        holder.viewNumReps.setText(listaEjercicios.get(position).getNumRepes());
        holder.viewPeso.setText(listaEjercicios.get(position).getPeso());
    }

    @Override
    public int getItemCount() {
        return listaEjercicios.size();
    }

    public class EjercicioViewHolder extends RecyclerView.ViewHolder {

        TextView viewEjercicio, viewNumReps, viewPeso;
        public EjercicioViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            //¿txtEjercicio,txtrepeticiones?
            viewEjercicio = itemView.findViewById(R.id.viewEjercicio);
            viewNumReps = itemView.findViewById(R.id.viewNumReps);
            viewPeso = itemView.findViewById(R.id.viewPeso);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent = new Intent(context, AmpliarActivity.class);
                    intent.putExtra("ID",listaEjercicios.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
