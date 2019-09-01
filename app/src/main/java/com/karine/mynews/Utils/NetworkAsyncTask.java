package com.karine.mynews.Utils;

import java.lang.ref.WeakReference;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class NetworkAsyncTask extends android.os.AsyncTask<String, Void, String>{

    public interface Listeners {

        void onPreExecute();
        void doInBackground();
        void onPostExecute(String success);
    }

    private final WeakReference<Listeners> callback;

    public NetworkAsyncTask(Listeners callback) {
        this.callback = new WeakReference<>(callback);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.callback.get().onPreExecute();
    }

    @Override
    protected void onPostExecute(String success) {
        super.onPostExecute(success);
        this.callback.get().onPostExecute(success);
    }

    @Override
    protected String doInBackground(String...url) {
        this.callback.get().doInBackground();
        return NytHTTPConnection.startHttpRequest(url[0]);
    }
}
