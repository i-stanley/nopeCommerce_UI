package org.nopeCommerce_ui.core;

public class VideoContext {
    private static final ThreadLocal<String> VIDEO_NAME = new ThreadLocal<>();

    public static void setVideoName(String name) {
        VIDEO_NAME.set(name);
    }

    public static String getVideoName() {
        return VIDEO_NAME.get();
    }

    public static void clear() {
        VIDEO_NAME.remove();
    }
}
