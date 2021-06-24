package activitytest.com.example.downloadpractice;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import javax.xml.transform.OutputKeys;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask extends AsyncTask<String,Integer,Integer> {

    public final static int TYPE_SUCCESS = 0;
    public final static int TYPE_FAILED = 1;
    public final static int TYPE_PAUSED = 2;
    public final static int TYPE_CANCELED = 3;

    private boolean isPaused = false;
    private boolean isCanceled = false;

    private DownloadListener downloadListener;
    public int lastProgress = 0;

    public DownloadTask(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;
        try{
            String downloadUrl = params[0];
            long downloadedLength = 0;
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory+fileName);
            if(file.exists()){
                downloadedLength = file.length();
            }
            long contengLength = getContentLength(downloadUrl);
            if(contengLength == 0){
                return TYPE_FAILED;
            }else if(contengLength  == downloadedLength){
                return TYPE_SUCCESS;
            }

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(downloadUrl)
                    .addHeader("RANGE","byte="+downloadedLength+"-")
                    .build();
            Response response = client.newCall(request).execute();
            if(response != null){
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file,"rw");
                savedFile.seek(downloadedLength);                   //跳过已经下载的字节
                int total = 0;
                int len;
                byte[] b = new byte[1024];
                while((len = is.read(b)) != -1){
                    if(isPaused){
                        return TYPE_PAUSED;
                    }else if(isCanceled){
                        return TYPE_CANCELED;
                    }else{
                        total += len;
                        savedFile.write(b,0,len);               //参数
                        int progress = (int) ((total + downloadedLength) * 100 / contengLength);
                        publishProgress(progress);
                    }
                }
                response.body().close();                                //关流
                return TYPE_SUCCESS;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(is != null){
                    is.close();
                }
                if(savedFile != null){
                    savedFile.close();
                }
                if(isCanceled && file != null){
                    file.delete();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();
        if(response != null && response.isSuccessful()){
            long contentLength = response.body().contentLength();
            response.body().close();
            return contentLength;
        }
        return 0;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer){
            case TYPE_CANCELED:
                downloadListener.canceled();
                break;
            case TYPE_FAILED:
                downloadListener.failed();
                break;
            case TYPE_PAUSED:
                downloadListener.paused();
                break;
            case TYPE_SUCCESS:
                downloadListener.success();
                break;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if(progress > lastProgress){
            downloadListener.progress(progress);
            lastProgress = progress;
        }
    }

    public void pauseDownload(){
        isPaused = true;
    }

    public void cancelDownload(){
        isCanceled = true;
    }
}
