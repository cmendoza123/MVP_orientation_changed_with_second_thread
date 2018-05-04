package charlieandroidblog.esy.es.asynktaskinrotationchanged;

import charlieandroidblog.esy.es.asynktaskinrotationchanged.Fragment.FileInSD;

/**
 * Created by carlosvillelaymendoza on 5/3/18.
 */

public interface IThreadCallBack {


    void updateValues(int percentage);

    void readerCompleted(FileInSD[] aFiles);

    void readerCompletedWithError(String msg);

}
