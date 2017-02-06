package homepunk.lesson.series.interfaces;

public interface Listeners {
        interface RetrofitListener<T> {
                void onResult(T series);

                void onError(String e);
        }
}
