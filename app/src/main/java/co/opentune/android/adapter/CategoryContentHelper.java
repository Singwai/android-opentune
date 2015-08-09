package co.opentune.android.adapter;

import java.util.ArrayList;

import co.opentune.android.R;
import co.opentune.android.entity.Category;

/**
 * Created by Singwai Chan on 8/8/15.
 */
public class CategoryContentHelper {

    public static final ArrayList<Category> categories = new ArrayList<>(9);

    static {
        categories.add(new Category("All Genres" , R.drawable.category_hip_hop));
        categories.add(new Category("Electronic" , R.drawable.category_hip_hop));
        categories.add(new Category("Hip Hop" , R.drawable.category_hip_hop));
        categories.add(new Category("Dance" , R.drawable.category_hip_hop));
        categories.add(new Category("a" , R.drawable.category_hip_hop));
        categories.add(new Category("a" , R.drawable.category_hip_hop));
    }
}
