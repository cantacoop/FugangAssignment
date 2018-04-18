package com.baiyai.android.fuganghomework;

public final class Utilities {

    public static int getPhotoResourceId(String photoName) {

        switch (photoName) {
            case "iron_man":
                return R.drawable.iron_man;
            case "thor":
                return R.drawable.thor;
            case "ant_man":
                return R.drawable.ant_man;
            case "wash":
                return R.drawable.wash;
            case "hulk":
                return R.drawable.hulk;
            case "captain_america":
                return R.drawable.captain_america;
            case "hawkeye":
                return R.drawable.hawkeye;
            case "quick_silver":
                return R.drawable.quick_silver;
            case "scarlet_witch":
                return R.drawable.scarlet_witch;
            case "swordsman":
                return R.drawable.swordsman;
            default:
                return R.drawable.sample_0;
        }
    }
}
