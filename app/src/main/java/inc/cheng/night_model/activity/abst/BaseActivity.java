package inc.cheng.night_model.activity.abst;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;

import inc.cheng.night_model.R;
import inc.cheng.night_model.util.acache.ACache;
import inc.cheng.night_model.util.acache.ACacheUtil;

public abstract class BaseActivity  extends AppCompatActivity {
    protected Context context;
    protected Handler handler = new Handler();
    protected ACache aCache;
    protected String current_theme;
    protected ImmersionBar immersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initContext();
        initACache();
        // 在这里设置主题
        setAppTheme();
        super.onCreate(savedInstanceState);
        setContentView(mainViewId());
        initImmersionBar();//初始化 通知栏
        initComponent();//初始化组件
        initData();//加载数据
        setPage();// 显示数据
        setListener();// 绑定监听
    }
    private void initContext(){
        context = this;
    }
    private void initACache(){
        aCache = ACacheUtil.getACache(context);
    }
    private void setAppTheme(){
        String cache_theme = aCache.getAsString("theme");
        cache_theme = null != cache_theme ? cache_theme : "default_model";
        switch (cache_theme) {
            case "default_model" : {
                setTheme(R.style.default_model);
                break;
            }
            case "night_model" : {
                setTheme(R.style.night_model);
                break;
            }
        }
        current_theme = cache_theme;
    }
    private void initImmersionBar(){
        immersionBar = ImmersionBar.with(this);
        boolean flag_default_theme = "default_model".equals(aCache.getAsString("theme"));
        immersionBar.statusBarDarkFont(flag_default_theme).init();
    }

    // 切换主题 在子类中直接调用即可 或者定义多种主题 实现的方式是类似的
    protected void toggleTheme(){
        /** 获取当前主题，并改换成 非当前主题  **/
        boolean flag_default_theme = "default_model".equals(aCache.getAsString("theme"));
        aCache.put("theme" , flag_default_theme ? "night_model" : "default_model");
        setTheme(flag_default_theme ? R.style.night_model : R.style.default_model);
        recreate();
    }

    protected abstract int mainViewId();

    protected abstract void initComponent();

    protected abstract void initData();

    protected abstract void setPage();

    protected abstract void setListener();
}
