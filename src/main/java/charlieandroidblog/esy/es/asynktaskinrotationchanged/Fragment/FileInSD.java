package charlieandroidblog.esy.es.asynktaskinrotationchanged.Fragment;

/**
 * Created by carlosvillelaymendoza on 5/3/18.
 */

public class FileInSD {

    String name;
    long size;

    public FileInSD(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
