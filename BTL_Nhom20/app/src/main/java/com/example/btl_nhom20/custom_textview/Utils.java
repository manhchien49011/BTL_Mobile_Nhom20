package com.example.btl_nhom20.custom_textview;

import android.content.Context;
import android.graphics.Typeface;

public class Utils {

    private static Typeface LobsterTypeface;
    private static Typeface RobotoBold;

    public static Typeface getRobotoBold(Context context) {
        if(LobsterTypeface == null){
            RobotoBold = Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Bold.ttf");
        }
        return RobotoBold;
    }

    public static Typeface getLobsterTypeface(Context context) {
        if(LobsterTypeface == null){
            LobsterTypeface = Typeface.createFromAsset(context.getAssets(),"fonts/Lobster_1.3.otf");
        }
        return LobsterTypeface;
    }


}
