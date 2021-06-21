package activitytest.com.example.servicebestpractice;

public interface DownloadListener {
    void onProgress(int progress);
    void onSucceed();
    void onFailed();
    void onPause();
    void onCanceled();
}
