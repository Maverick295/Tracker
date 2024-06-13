package com.tracker.tracker.utils;

import java.util.UUID;

public class ServiceUtil {

    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
