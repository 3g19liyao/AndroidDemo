package activitytest.com.example.listview1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Things> thingsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        ThingsAdapter adapter = new ThingsAdapter(MainActivity.this,R.layout.things,thingsList);
        ListView listView = (ListView)findViewById(R.id.list_item);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Things things = thingsList.get(i);
                Toast.makeText(MainActivity.this,things.getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void init() {
        for(int i = 0;i < 2;i++){
            Things thing = new Things(R.drawable.apple_item,"图片1");
            thingsList.add(thing);
            Things thing1 = new Things(R.drawable.banana_item,"图片2");
            thingsList.add(thing1);
            Things thing2 = new Things(R.drawable.cherry_item,"图片3");
            thingsList.add(thing2);
            Things thing3 = new Things(R.drawable.grape_item,"图片4");
            thingsList.add(thing3);
            Things thing4 = new Things(R.drawable.kiwi__easyico,"图片5");
            thingsList.add(thing4);
            Things thing5 = new Things(R.drawable.mango_item,"图片6");
            thingsList.add(thing5);
            Things thing6 = new Things(R.drawable.orange_item,"图片7");
            thingsList.add(thing6);
            Things thing7 = new Things(R.drawable.pear_item,"图片8");
            thingsList.add(thing7);
            Things thing8 = new Things(R.drawable.strawberry_item,"图片9");
            thingsList.add(thing8);
        }
    }

}