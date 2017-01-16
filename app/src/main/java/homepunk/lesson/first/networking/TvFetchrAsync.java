package homepunk.lesson.first.networking;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TvFetchrAsync extends AsyncTask<String, Integer, String> {
    public IResultListener listener;

    public TvFetchrAsync(IResultListener listener) {
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
            listener.onError("ERROR");
            e.printStackTrace();
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    listener.onError("ERROR");
                    e.printStackTrace();
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

    private URL createUrl(String stringUrl){
        URL url;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return url;
    }

    private InputStream makeHttpRequest(URL url){
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
        } catch (IOException e) {
            listener.onError(e.getMessage());
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            } if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    listener.onError(e.getMessage());
                }
            }
        }
        return inputStream;
    }

    private String extractFromJson(InputStream inputStream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();

        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null)
            stringBuffer.append(line + "\n");

        if (stringBuffer == null) {
            listener.onError("Server response is empty");
            return null;
        }
        return stringBuffer.toString();
    }

    public interface IResultListener {
        void onResult(String result);

        void onError(String error);
    }
}

