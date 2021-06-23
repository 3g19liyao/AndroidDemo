package activitytest.com.example.recyclerview1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ThingsAdapter extends RecyclerView.Adapter {

    private List<Things> mThingsList = new ArrayList<>();

    public ThingsAdapter(List<Things> thingsList) {
        this.mThingsList = thingsList;
    }

    public interface onItemClickListen {
        void onItemCLick(View view,int position);
    }

    private onItemClickListen onItemClickListen;

    public void setOnItemClickListen(onItemClickListen onItemClickListen){
        this.onItemClickListen = onItemClickListen;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View thingsView;
        ImageView thingsImage;
        TextView thingsName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thingsView = itemView;
            thingsImage = (ImageView) itemView.findViewById(R.id.things_image);
            thingsName = (TextView) itemView.findViewById(R.id.things_name);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.things,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ImageView thingsImage;
        TextView thingsName;
        thingsImage = (ImageView) viewHolder.itemView.findViewById(R.id.things_image);
        thingsName = (TextView) viewHolder.itemView.findViewById(R.id.things_name);
        Things things = mThingsList.get(position);
        thingsName.setText(things.getName());;
        thingsImage.setImageResource(things.getImageId());
    }

    @Override
    public int getItemCount() {
        return mThingsList.size();
    }
}
