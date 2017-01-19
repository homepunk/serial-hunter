package homepunk.lesson.first.networking;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TVFetchrAsync extends AsyncTask<String, Integer, String> {
    public IResultListener listener;

    public TVFetchrAsync(IResultListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String json = null;

        try {
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            if (inputStream == null) {
                listener.onError("Error to read input stream");
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null)
                stringBuffer.append(line + "\n");

            if (stringBuffer == null) {
                listener.onError("Server response is empty");
                return null;
            }
            json = stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            listener.onError(e.getMessage());
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onError(e.getMessage());
                }
            }
        }
        return json;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (result == null) {
            return;
        }

        listener.onResult(result);
    }

    public interface IResultListener {
        void onResult(String result);

        void onError(String error);
    }
}