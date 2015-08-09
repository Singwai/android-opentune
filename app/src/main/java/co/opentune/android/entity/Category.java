package co.opentune.android.entity;

/**
 * Created by Singwai Chan on 8/8/15.
 */
public class Category extends Item{

    public final String title;
    public final String id;
    public final int drawableIcon;

    public Category(String title, String id, int drawableIcon){
        super();
        this.title = title;
        this.id = id;
        this.drawableIcon = drawableIcon;
    }

    public Category(String title, int drawableIcon){
        this(title, "" , drawableIcon);
    }
}
