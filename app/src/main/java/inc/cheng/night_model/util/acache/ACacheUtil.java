package inc.cheng.night_model.util.acache;

import android.content.Context;

public class ACacheUtil {
    public static ACache getACache(Context context){
        return ACache.get(context);
    }
}
