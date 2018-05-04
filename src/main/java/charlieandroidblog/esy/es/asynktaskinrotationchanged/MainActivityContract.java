package charlieandroidblog.esy.es.asynktaskinrotationchanged;

import android.app.Fragment;
import android.widget.Button;

import java.io.Serializable;

/**
 * Created by carlosvillelaymendoza on 5/3/18.
 */

interface MainActivityContract {

    interface Presenter extends Serializable {

        void updateView(MainActivityContract.View view);

        void startReading();

        void stopReading();

        void checkForCompletation();

        void setPermissionResults(boolean isPermissionGranted);

    }

    interface View{

        void showLoadingView(int progress);

        void showLoadingView();

        void hideLoadingView();

        void showResults(String results);

        void showPermissionsResultError();

        void showErrorMsg(String cad);

    }

}
