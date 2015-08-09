package co.opentune.android.adapter;

import java.util.ArrayList;

import co.opentune.android.entity.Category;

/**
 * Created by Singwai Chan on 8/8/15.
 */
public class CategoryContentHelper {

    public static final ArrayList<Category> categories = new ArrayList<>(9);

    static {
        categories.add(new Category("All Genres" , 0));
        categories.add(new Category("Electronic" , 0));
        categories.add(new Category("Hip Hop" , 0));
        categories.add(new Category("Dance" , 0));
        categories.add(new Category("a" , 0));
        categories.add(new Category("a" , 0));
    }
}
