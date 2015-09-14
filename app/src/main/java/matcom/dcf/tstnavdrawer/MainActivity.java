package matcom.dcf.tstnavdrawer;

import android.app.ActionBar;
import android.app.Activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PrivateKey;
import java.util.PriorityQueue;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    private DrawerLayout drawerLayout;
    private ListView listview;
    private ActionBarDrawerToggle drawerListener;
    private  MyAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(MainActivity.this, "Drawer Open", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(MainActivity.this, "Drawer Close", Toast.LENGTH_SHORT).show();
            }
        };
        // SEt listener
        drawerLayout.setDrawerListener(drawerListener);

        listview = (ListView) findViewById(R.id.drawerList);
        //
        //listview.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,planets));
        listview.setAdapter(new MyAdapter(this));
        listview.setOnItemClickListener(this);
        // Manage use of Nav Drawer in action
        // Enable action bar area
        getActionBar().setHomeButtonEnabled(true);
        //
        getActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Forward event to navigation drawer
        if (drawerListener.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // manage orientation change
        drawerListener.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Synchronise drawer state three lines will be displayed user will see that there is a navigation drawer and image
        drawerListener.syncState();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "The following planet has been selected  ", Toast.LENGTH_LONG).show();
        selectItem(position);
    }

    public void selectItem(int position) {
        listview.setItemChecked(position, true);
        //setTitle(planets[position]);
    }

    public void setTitle(String title) {
        getActionBar().setTitle(title);

    }

}

class MyAdapter extends BaseAdapter {
    String[] socialSites;
    int[] images = {R.drawable.google,
            R.drawable.twitter,
            R.drawable.facebook,
            R.drawable.pintrest};
    private Context context;

    public MyAdapter(Context context) {
        socialSites = context.getResources().getStringArray(R.array.social);
        this.context = context;
    }

    @Override
    public int getCount() {

        return socialSites.length;
    }

    @Override
    public Object getItem(int position) {

        return socialSites[position];
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=null;
        if (convertView == null) {
            LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.customrow,parent,false);

        } else {
            row = convertView;

        }
        ImageView img =  (ImageView) row.findViewById(R.id.imageView);
        TextView  tv = (TextView) row.findViewById(R.id.textView);
        //
        tv.setText(socialSites[position]);
        img.setImageResource(images[position]);
        return row;
    }
}
