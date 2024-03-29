package com.example.hustle4work.utility;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;

import com.example.hustle4work.R;

public class CustomProgressDialog extends Dialog {
    Context context;
    ProgressBar pBar;

    public CustomProgressDialog(Context paramContext)
    {
        super(paramContext);
        this.context = paramContext;

    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//    getWindow().setWindowAnimations(R.style.Dialog_animation);
        setContentView(R.layout.custome_progress);
        pBar=(ProgressBar)findViewById(R.id.custom_progress);
        setCancelable(false);
//    setCanceledOnTouchOutside(true);

    }

    public void setOnCancelListener(OnCancelListener paramOnCancelListener)
    {
        super.setOnCancelListener(paramOnCancelListener);
    }

    public void setOnDismissListener(OnDismissListener paramOnDismissListener)
    {
        super.setOnDismissListener(paramOnDismissListener);
    }
}
