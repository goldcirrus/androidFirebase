package com.example.cloudtutor.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static  List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     * ID is assigned by the order of the ITEM when it is added into this map.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.name, item);
    }

    public static void clearList(){
        //ITEMS = new ArrayList<DummyItem>();
          ITEMS.clear();
    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String name;
        public final String classes;
        public final String details;

        /**DummyItem Constructor. */
        public DummyItem(String name, String classes, String details) {
            this.name = name;
            this.classes = classes;
            this.details = details;
        }

        @Override
        public String toString() {
            return name+classes+details;
        }
    }
}
