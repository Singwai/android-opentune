package co.opentune.android.entity;

/**
 * Created by Singwai Chan on 8/8/15.
 */
public class Category extends Item{

    public final String title;
    public final int drawableIcon;

    public Category(String title, int drawableIcon){
        super();
        this.title = title;
        this.drawableIcon = drawableIcon;
    }
}
