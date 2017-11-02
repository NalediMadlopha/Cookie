package app.cookie.app._new_architecture.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "step", foreignKeys = {
        @ForeignKey(
                entity = Recipe.class,
                parentColumns = "id",
                childColumns = "recipe_id",
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE
        )
}, indices = @Index("recipe_id"))
public class Step implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "shortDescription")
    private String shortDescription;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "videoURL")
    private String videoURL;
    @ColumnInfo(name = "thumbnailURL")
    private String thumbnailURL;
    @ColumnInfo(name = "recipe_id")
    private int recipeId;

    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel source) {
            return new Step(source);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public Step(int id, String shortDescription, String description, String videoURL, String thumbnailURL, int recipeId) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
        this.recipeId = recipeId;
    }

    protected Step(Parcel in) {
        this.id = in.readInt();
        this.shortDescription = in.readString();
        this.description = in.readString();
        this.videoURL = in.readString();
        this.thumbnailURL = in.readString();
        this.recipeId = in.readInt();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.shortDescription);
        dest.writeString(this.description);
        dest.writeString(this.videoURL);
        dest.writeString(this.thumbnailURL);
        dest.writeInt(this.recipeId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Step step = (Step) o;

        if (getId() != step.getId()) return false;
        if (getRecipeId() != step.getRecipeId()) return false;
        if (getShortDescription() != null ? !getShortDescription().equals(step.getShortDescription()) : step.getShortDescription() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(step.getDescription()) : step.getDescription() != null)
            return false;
        if (getVideoURL() != null ? !getVideoURL().equals(step.getVideoURL()) : step.getVideoURL() != null)
            return false;
        return getThumbnailURL() != null ? getThumbnailURL().equals(step.getThumbnailURL()) : step.getThumbnailURL() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getShortDescription() != null ? getShortDescription().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getVideoURL() != null ? getVideoURL().hashCode() : 0);
        result = 31 * result + (getThumbnailURL() != null ? getThumbnailURL().hashCode() : 0);
        result = 31 * result + getRecipeId();
        return result;
    }
}
