package app.cookie.app.data;


import app.cookie.app.model.Recipe;
import app.cookie.app.stringdef.CookieConstants;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET(CookieConstants.URL.PATH_URL)
    Call<Recipe[]> getRecipes();
}
