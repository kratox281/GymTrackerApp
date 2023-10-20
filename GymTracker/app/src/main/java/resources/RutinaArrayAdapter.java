package resources;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gymtracker.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Map;

public class RutinaArrayAdapter extends ArrayAdapter<Rutina>{
    public RutinaArrayAdapter(@NonNull Context context, int resource, @NonNull List<Rutina> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater constructor = LayoutInflater.from(getContext());
        View item = constructor.inflate(R.layout.listviewejercicios,null);
        Rutina rutina = getItem(position);
        Map<String,Integer> ejercicios = rutina.getEjercicios();
        String[]claves = ejercicios.keySet().toArray(new String[ejercicios.size()]);
        ((TextView)item.findViewById(R.id.tv_ejercicioRutina)).setText(claves[position]);
        ((TextView)item.findViewById(R.id.tv_numeroSeries)).setText(ejercicios.get(claves[position]));


        return item;
    }
}
