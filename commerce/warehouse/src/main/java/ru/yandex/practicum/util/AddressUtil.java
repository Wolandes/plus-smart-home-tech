package ru.yandex.practicum.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Утилита для работы с адресами
 */
public final class AddressUtil {
    private static final String[] ADDRESSES =
            new String[] {"ADDRESS_1", "ADDRESS_2"};

    private static final String CURRENT_ADDRESS =
            ADDRESSES[Random.from(new SecureRandom()).nextInt(0, 1)];

    public static String getAddress() {
        return CURRENT_ADDRESS;
    }
}
