package com.byteamaze.libpbkdf2;

import org.jetbrains.annotations.NotNull;

/**
 * native pbkdf2 crypto class
 */
public class Crypto {

    /**
     * Singleton
     */
    public static final Crypto shared = new Crypto();

    static {
        System.loadLibrary("pbkdf2");
    }

    private native byte[] pbkdf2(String password, byte[] salt, int keyLength, int rounds);

    /**
     * PBKDF2 Crypt
     *
     * @param password
     * @param salt
     * @param keyLength
     * @param rounds
     * @return
     * @throws IllegalArgumentException
     */
    public byte[] doPbkdf2(@NotNull char[] password, @NotNull byte[] salt,
                           int keyLength, int rounds) throws IllegalArgumentException {
        String passwordStr = String.valueOf(password);
        checkArguments(passwordStr, salt);
        return pbkdf2(passwordStr, salt, keyLength, rounds);
    }

    /**
     * PBKDF2 Crypt
     *
     * @param password
     * @param salt
     * @param keyLength
     * @param rounds
     * @return
     * @throws IllegalArgumentException
     */
    public byte[] doPbkdf2(@NotNull String password, @NotNull byte[] salt,
                           int keyLength, int rounds) throws IllegalArgumentException {
        checkArguments(password, salt);
        return pbkdf2(password, salt, keyLength, rounds);
    }

    /**
     * check arguments
     *
     * @param password
     * @param salt
     */
    private void checkArguments(String password, byte[] salt) throws IllegalArgumentException {
        // check salt
        if (salt == null || salt.length == 0 || salt.length > 8)
            throw new IllegalArgumentException("salt must be 8 bytes length");

        // check password
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("password can not be empty");
    }

}
