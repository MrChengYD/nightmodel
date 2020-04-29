package inc.cheng.night_model.activity;

import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import inc.cheng.night_model.R;
import inc.cheng.night_model.activity.abst.BaseActivity;
import inc.cheng.night_model.adapter.TestAdapter;

public class MainActivity extends BaseActivity {
    protected Toolbar toolbar;

    private ActionBar actionBar;

    private ImageView theme_img;
    private RecyclerView recycleView;
    private TestAdapter testAdapter;
    @Override
    protected int mainViewId() {
        return R.layout.layout_main;
    }

    @Override
    protected void initComponent() {
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if(null != actionBar){
            //actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setHomeButtonEnabled(withBack());
            //actionBar.setDisplayShowTitleEnabled(true);
        }
        theme_img = findViewById(R.id.theme_img);
        boolean flag_default_theme = "default_model".equals(aCache.getAsString("theme"));
        theme_img.setImageResource(flag_default_theme ? R.drawable.icon_moon : R.drawable.icon_sun);

        recycleView = findViewById(R.id.recycleView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        mLayoutManager.setStackFromEnd(false);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLayoutManager.setReverseLayout(false);
        recycleView.setLayoutManager(mLayoutManager);
        recycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setPage() {
        List<String> source_temp = Arrays.asList("1" , "2" , "1" , "2" , "1" , "2" , "1" , "2" , "1" , "2" , "1" , "2" , "1" , "2" , "1" , "2" , "1" , "2" , "1" , "2" , "1" , "2" , "1" , "2" , "1" , "2" , "1" , "2");
        testAdapter = new TestAdapter(context , source_temp);
        recycleView.setAdapter(testAdapter);
    }

    @Override
    protected void setListener() {
        theme_img.setOnClickListener(v -> {
            toggleTheme();
        });
    }

}
