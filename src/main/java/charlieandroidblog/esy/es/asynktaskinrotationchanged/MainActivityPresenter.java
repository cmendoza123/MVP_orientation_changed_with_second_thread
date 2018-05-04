package charlieandroidblog.esy.es.asynktaskinrotationchanged;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.Serializable;

import charlieandroidblog.esy.es.asynktaskinrotationchanged.Fragment.FileInSD;
import charlieandroidblog.esy.es.asynktaskinrotationchanged.Fragment.ReaderThread;

/**
 * Created by carlosvillelaymendoza on 5/3/18.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter, IThreadCallBack, Serializable{

    private static final long serialVersionUID = 3L;

    MainActivityContract.View view;
    ICallback parentActivity;
    ReaderThread readerThread;

    boolean isTaskCompleted = false;
    String resultsFromTaskt = null;
    boolean isPermissionGranted = false;

    public MainActivityPresenter(ICallback activity, MainActivityContract.View view) {
        this.view = view;
        this.parentActivity = activity;

        readerThread = new ReaderThread(this);
    }


    @Override
    public void updateView( MainActivityContract.View view) {
        this.view = view;

    }

    @Override
    public void startReading() {

        if (!this.isPermissionGranted){
            view.showPermissionsResultError();
            return;
        }

        if (readerThread.getEstadoRunnable() != ReaderThread.EstadoRunnable.RUNNING){
            File sdCardDir = Environment.getExternalStorageDirectory();
            readerThread.start();
            view.showLoadingView();
        }
    }

    @Override
    public void stopReading() {
        if (readerThread.getEstadoRunnable() == ReaderThread.EstadoRunnable.RUNNING){
            readerThread.interrupt();
            readerThread.setEstadoRunnable(ReaderThread.EstadoRunnable.NON_STARTED);
            view.hideLoadingView();
        }
    }

    @Override
    public void updateValues(int percentage) {
        //this time just show the loading views..
        view.showLoadingView();
    }

    @Override
    public void readerCompleted(FileInSD[] aFiles) {

        String results = "";
        for (FileInSD file : aFiles){
            results += file.getName()+"-"+file.getSize()+"\n";
        }

        setSecundaryTaskCompleted(true,results);
        view.hideLoadingView();
        view.showResults(resultsFromTaskt);
    }

    @Override
    public void readerCompletedWithError(String msg) {
        view.hideLoadingView();
        view.showErrorMsg(msg);
    }

    @Override
    public void checkForCompletation() {
       switch (readerThread.getEstadoRunnable()){
           case NON_STARTED: view.hideLoadingView(); break;
           case RUNNING: view.showLoadingView(); break;
           case ENDED: view.hideLoadingView();
                        view.showResults("");
       }
    }

    private void setSecundaryTaskCompleted(boolean isCompleted, String results){
        this.isTaskCompleted = isCompleted;
        this.resultsFromTaskt = results;
    }

    @Override
    public void setPermissionResults(boolean isPermissionGranted) {
        this.isPermissionGranted = isPermissionGranted;
    }


}
