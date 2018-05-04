package charlieandroidblog.esy.es.asynktaskinrotationchanged.Fragment;

/**
 * Created by carlosvillelaymendoza on 5/3/18.
 */

public class Auxiliar {

    public static void tryToAddFileToArray(FileInSD[] aFiles, FileInSD fileInSD) {
        for (int n=0;n<10;n++){
            if (aFiles[n]==null) {
                aFiles[n]=fileInSD;
                return;
            }
        }
        int indexOfSmallerFileSizeOnArray = getIndexOfSmallerFileInArray(aFiles);
        if (aFiles[indexOfSmallerFileSizeOnArray].getSize() > fileInSD.getSize())
            aFiles[indexOfSmallerFileSizeOnArray] = fileInSD;
    }

    private static int getIndexOfSmallerFileInArray(FileInSD[] aFiles) {
        int minIndex=0;
        for (int n=1;n<10;n++){
            if (aFiles[n].getSize()<aFiles[minIndex].getSize())
                minIndex = n;
        }
        return minIndex;
    }


}
