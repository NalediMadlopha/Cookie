package app.cookie.app.service;

import app.cookie.app._new_architecture.service.WebService;
import app.cookie.app.stringdef.CookieConstants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static final String BASE_URL = CookieConstants.URL.BASE_URL;

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static WebService getApiService() {
        return getRetrofitInstance().create(WebService.class);
    }
}
