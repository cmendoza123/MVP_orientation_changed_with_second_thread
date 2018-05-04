package charlieandroidblog.esy.es.asynktaskinrotationchanged;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by carlosvillelaymendoza on 5/3/18.
 */

public class MainActivityView implements MainActivityContract.View, Serializable {

    Activity context;

    public MainActivityView(Activity context) {
        this.context = context;
    }

    @Override
    public void showLoadingView(final int progress) {
        final TextView tv = context.findViewById(R.id.tv_percent);
        showLoadingView();
        if (tv!=null){
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setVisibility(View.VISIBLE);
                tv.setText(progress);
            }
        }); } else{
            Log.d("zzz", "showLoadingValue tv is null");
        }
    }

    @Override
    public void showLoadingView() {
        final LinearLayout llContainerLoader = (LinearLayout) context.findViewById(R.id.ll_container_progress_bar);
        if (llContainerLoader!=null){
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    llContainerLoader.setVisibility(View.VISIBLE);
                }
            });
        } else{
            Log.d("zzz", "showLoadingValue ll is null");
        }
    }

    @Override
    public void showResults(final String results) {
        //final TextView tv = context.findViewById(R.id.tv_contenido);
        final TextView tv = (TextView) context.findViewById(R.id.lv_results);
        if (tv!=null){
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv.setVisibility(View.VISIBLE);
                    tv.setText(results);
                }
            });
        } else {
            Log.d("zzz", "showLoadingValue tv is null");
        }
    }

    @Override
    public void hideLoadingView() {
        final LinearLayout llContainerLoader = (LinearLayout) context.findViewById(R.id.ll_container_progress_bar);
        if (llContainerLoader!=null){
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    llContainerLoader.setVisibility(View.INVISIBLE);
                }
            }); } else {
            Log.d("zzz", "showLoadingValue tv is null");
        }
    }

    @Override
    public void showPermissionsResultError() {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context,"Please give permission to use this feature",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void showErrorMsg(String cad) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context,"Error reading SD CARD",Toast.LENGTH_LONG).show();
            }
        });
    }
}
