package app.cookie.app.service;


import app.cookie.app.model.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

import static app.cookie.app.stringdef.CookieConstants.URL.BASE_URL;

public interface WebService {

    @GET(BASE_URL)
    Call<Recipe[]> getRecipes();
}
