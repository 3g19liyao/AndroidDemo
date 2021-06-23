package activitytest.com.example.listview1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ThingsAdapter extends ArrayAdapter<Things> {

    private int resourceId;

    public ThingsAdapter(@NonNull Context context, int resource, @NonNull List<Things> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        Things things = getItem(position);
        View view;
        ViewHolder viewHolder;

        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView)view.findViewById(R.id.text_item);
            viewHolder.imageView = (ImageView)view.findViewById(R.id.image_item);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.imageView.setImageResource(things.getImageId());
        viewHolder.textView.setText(things.getName());
        return view;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }

}
