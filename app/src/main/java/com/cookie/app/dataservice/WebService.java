package com.cookie.app.dataservice;


import com.cookie.app.model.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

import static com.cookie.app.stringdef.CookieConstants.URL.PATH_URL;

public interface WebService {

    @GET(PATH_URL)
    Call<Recipe[]> getRecipes();
}
