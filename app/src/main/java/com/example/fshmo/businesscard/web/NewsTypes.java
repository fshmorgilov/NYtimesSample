package com.example.fshmo.businesscard.web;

import java.util.Arrays;

public enum NewsTypes {
    home,
    opinion,
    world,
    national,
    politics,
    upshot,
    nyregion,
    business,
    technology,
    science,
    health,
    sports,
    arts,
    books,
    movies,
    theater,
    sundayreview,
    fashion,
    tmagazine,
    food,
    travel,
    magazine,
    realestate,
    automobiles,
    obituaries,
    insider;

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
    }
}
