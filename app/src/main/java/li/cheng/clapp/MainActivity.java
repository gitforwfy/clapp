package li.cheng.clapp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import li.cheng.clapp.activity.SetActivity;
import li.cheng.clapp.adapter.HomeAdapter;
import li.cheng.clapp.service.MainService;

public class MainActivity extends Activity implements HomeAdapter.OnItemClickListener {
    private RecyclerView rv;
    private HomeAdapter mAdapter;
    int spanCount = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadApps();
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(this, spanCount));
        rv.setAdapter(mAdapter = new HomeAdapter(this, apps));
        mAdapter.setOnItemClickListener(this);
        MainService.StartService(this);
    }

    private List<ResolveInfo> apps;

    private void loadApps() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        new ImageView(MainActivity.this);
        apps = getPackageManager().queryIntentActivities(mainIntent, 0);
    }

    @Override
    public void onItemClick(View view, int position) {
        ResolveInfo info = apps.get(position);
        //该应用的包名
        String pkg = info.activityInfo.packageName;
        //应用的主activity类
        String cls = info.activityInfo.name;
        ComponentName componet = new ComponentName(pkg, cls);

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(componet);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
        } catch (SecurityException e) {
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            return true;
        }

        if(KeyEvent.KEYCODE_MENU==keyCode){
            Intent intent=new Intent(this, SetActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
