package ru.lisitsyna.softwaredesign.actorsearch;

public enum Engine {
    YANDEX, BING, GOOGLE;

    public String toString() {
        switch (this) {
            case BING:
                return "Bing";
            case GOOGLE:
                return "Google";
            case YANDEX:
                return "Yandex";
        }
        return "";
    }
}
