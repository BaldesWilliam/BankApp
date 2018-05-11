package com.helpmeproductions.willus08.bankapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class DataStorage {

    @PrimaryKey
    @NonNull
    String password;
    byte[] encryptedAccountNumber;
    byte[] decryptingCipher;

    public DataStorage(String password, byte[] encryptedAccountNumber, byte[] decryptingCipher) {
        this.password = password;
        this.encryptedAccountNumber = encryptedAccountNumber;
        this.decryptingCipher = decryptingCipher;
    }

    public byte[] getEncryptedAccountNumber() {
        return encryptedAccountNumber;
    }

    public byte[] getDecryptingCipher() {
        return decryptingCipher;
    }

    @NonNull
    public String getPassword() {
        return password;
    }
}

