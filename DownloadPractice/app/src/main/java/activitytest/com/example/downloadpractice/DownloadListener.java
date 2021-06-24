package activitytest.com.example.downloadpractice;

public interface DownloadListener {
    void progress(int progress);
    void success();
    void failed();
    void paused();
    void canceled();
}
