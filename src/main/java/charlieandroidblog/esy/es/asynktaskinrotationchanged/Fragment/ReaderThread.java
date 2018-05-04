package charlieandroidblog.esy.es.asynktaskinrotationchanged.Fragment;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import charlieandroidblog.esy.es.asynktaskinrotationchanged.IThreadCallBack;

/**
 * Created by carlosvillelaymendoza on 5/3/18.
 */

public class ReaderThread extends Thread implements Serializable {

    public enum EstadoRunnable {
        NON_STARTED, RUNNING, ENDED
    }

    private EstadoRunnable estadoRunnable = EstadoRunnable.NON_STARTED;
    private IThreadCallBack presenter;

    FileInSD[] aFiles = {null,null,null,null,null,null,null,null,null,null};

    public ReaderThread(IThreadCallBack presenter) {
        this.presenter = presenter;
    }

    @Override
    public void run() {
        estadoRunnable = EstadoRunnable.RUNNING;

        //region code for trying oriention changes, ignore
//        int cont = 0;
//        while (cont<100){
//            cont++;
//            try {
//                Thread.sleep(100);
//                Log.d("zzz", "run: "+cont);
//                presenter.updateValues(cont);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                estadoRunnable = EstadoRunnable.NON_STARTED;
//                return;
//            }
//        }
        //endregion,

        readFiles();

        estadoRunnable = EstadoRunnable.ENDED;
        presenter.readerCompleted(aFiles);
    }

    public EstadoRunnable getEstadoRunnable() {
        return estadoRunnable;
    }

    public void setEstadoRunnable(EstadoRunnable estado) {
        this.estadoRunnable = estado;
    }

    private void readFiles() {
        String state = Environment.getExternalStorageState();

        if (!(state.equals(Environment.MEDIA_MOUNTED))) {
            presenter.readerCompletedWithError("No SD-CARD Found");
            return;
        } else {
            File folder = Environment.getExternalStorageDirectory();
            readAllFilesInFolder(folder);
        }
    }

    private void readAllFilesInFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                readAllFilesInFolder(fileEntry);
            } else {
                tryToAddFileToArray(fileEntry.getName(),fileEntry.length());
            }
        }
    }

    private void tryToAddFileToArray(String name, long size) {
        Auxiliar.tryToAddFileToArray(aFiles,new FileInSD(name,size));
    }


}
