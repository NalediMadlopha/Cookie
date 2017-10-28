package app.cookie.app._new_architecture.service;


import app.cookie.app._new_architecture.model.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

import static app.cookie.app.stringdef.CookieConstants.URL.PATH_URL;

public interface WebService {

    @GET(PATH_URL)
    Call<Recipe[]> getRecipes();
}
