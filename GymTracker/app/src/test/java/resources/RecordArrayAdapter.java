package resources;

import android.content.Context;
import android.view.*;
import android.widget.*;

import androidx.annotation.*;

import com.example.appgym.R;

import java.util.List;

public class RecordArrayAdapter extends ArrayAdapter<Record> {
    public RecordArrayAdapter(@NonNull Context context, int resource, @NonNull List<Record> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater constructor = LayoutInflater.from(getContext());
        View item = constructor.inflate(R.layout.listviewrecords,null);
        Record record = getItem(position);
        ((TextView)item.findViewById(R.id.tv_nombreEjercicioRecord)).setText(record.getEjercicio());
        ((TextView)item.findViewById(R.id.tv_PesoRecord)).setText(record.getPeso()+"");


        return super.getView(position, convertView, parent);
    }
}
