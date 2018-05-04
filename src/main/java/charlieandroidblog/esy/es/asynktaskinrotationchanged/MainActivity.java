package charlieandroidblog.esy.es.asynktaskinrotationchanged;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements ICallback, Serializable {

    private final String TAG_ASYNC = "asyncThread";
    MainActivityContract.View view;
    MainActivityContract.Presenter presenter;

    int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            this.presenter = (MainActivityContract.Presenter) savedInstanceState.getSerializable(TAG_ASYNC);
            this.view = new MainActivityView(this);
            this.presenter.updateView(view);
            this.presenter.checkForCompletation();
        } else {
            view = new MainActivityView(this);
            presenter = new MainActivityPresenter(this, view);
        }
        initPermissionsCheck();
    }

    private void initPermissionsCheck() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                presenter.setPermissionResults(true);
            } else {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.setPermissionResults(true);
                } else {
                    presenter.setPermissionResults(false);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        this.presenter.checkForCompletation();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(TAG_ASYNC, presenter);
        super.onSaveInstanceState(outState);
    }

    @Override
    public Activity presenterCallback() {
        return null;
    }

    public void btnStartSecondThreadClicked(View v) {
        presenter.startReading();
    }

    public void btnStopSecondThreadClicked(View v) {
        presenter.stopReading();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
