package app.cookie.app.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {

    private int id;
    private String ingredient;
    private String quantity;
    private String measure;

    private int recipeId;

    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    public Ingredient(int id, String ingredient, String quantity, String measure, int recipeId) {
        this.id = id;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.measure = measure;
        this.recipeId = recipeId;
    }

    protected Ingredient(Parcel in) {
        this.id = in.readInt();
        this.ingredient = in.readString();
        this.quantity = in.readString();
        this.measure = in.readString();
        this.recipeId = in.readInt();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    public String getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.ingredient);
        dest.writeString(this.quantity);
        dest.writeString(this.measure);
        dest.writeInt(this.recipeId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        if (getId() != that.getId()) return false;
        if (getRecipeId() != that.getRecipeId()) return false;
        if (getIngredient() != null ? !getIngredient().equals(that.getIngredient()) : that.getIngredient() != null)
            return false;
        if (getQuantity() != null ? !getQuantity().equals(that.getQuantity()) : that.getQuantity() != null)
            return false;
        return getMeasure() != null ? getMeasure().equals(that.getMeasure()) : that.getMeasure() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getIngredient() != null ? getIngredient().hashCode() : 0);
        result = 31 * result + (getQuantity() != null ? getQuantity().hashCode() : 0);
        result = 31 * result + (getMeasure() != null ? getMeasure().hashCode() : 0);
        result = 31 * result + getRecipeId();
        return result;
    }
}
