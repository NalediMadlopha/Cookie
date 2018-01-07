package com.cookie.app.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.cookie.app.database.Converters;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "recipe")
@TypeConverters(Converters.class)
public class Recipe implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "servings")
    private String servings;
    @ColumnInfo(name = "image")
    private String image;
    private List<Ingredient> ingredients = null;
    private List<Step> steps = null;

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Ignore
    public Recipe() {
    }

    public Recipe(int id, String name, String servings, String image, List<Ingredient> ingredients, List<Step> steps) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    protected Recipe(Parcel in) {
        this.id = in.readInt();
        this.id = in.readInt();
        this.name = in.readString();
        this.ingredients = in.createTypedArrayList(Ingredient.CREATOR);
        this.steps = in.createTypedArrayList(Step.CREATOR);
        this.servings = in.readString();
        this.image = in.readString();
        this.ingredients = new ArrayList<>();
        in.readList(this.ingredients, Ingredient.class.getClassLoader());
        this.steps = new ArrayList<>();
        in.readList(this.steps, Step.class.getClassLoader());
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getServings() {
        return servings;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public List<Step> getSteps() {
        return steps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.servings);
        dest.writeString(this.image);
        dest.writeList(this.ingredients);
        dest.writeList(this.steps);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (getId() != recipe.getId()) return false;
        if (getName() != null ? !getName().equals(recipe.getName()) : recipe.getName() != null)
            return false;
        if (getServings() != null ? !getServings().equals(recipe.getServings()) : recipe.getServings() != null)
            return false;
        if (getImage() != null ? !getImage().equals(recipe.getImage()) : recipe.getImage() != null)
            return false;
        if (getIngredients() != null ? !getIngredients().equals(recipe.getIngredients()) : recipe.getIngredients() != null)
            return false;
        return getSteps() != null ? getSteps().equals(recipe.getSteps()) : recipe.getSteps() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getServings() != null ? getServings().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + (getIngredients() != null ? getIngredients().hashCode() : 0);
        result = 31 * result + (getSteps() != null ? getSteps().hashCode() : 0);
        return result;
    }
}
