package homepunk.lesson.first.data.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static homepunk.lesson.first.data.database.Constants.BASE_URL;

public class TmdbClient {
    private static TmdbApi serviceExpose;
    private static TmdbApi service;

    private static Gson getGson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    public static TmdbApi getClientWithExposes(){
        if(serviceExpose == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
            serviceExpose = retrofit.create(TmdbApi.class);
        }
        return serviceExpose;
    }

    public static TmdbApi getClientWithoutExposes(){
        if(service == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(TmdbApi.class);
        }
        return service;
    }
}
