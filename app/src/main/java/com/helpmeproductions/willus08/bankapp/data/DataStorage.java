package com.helpmeproductions.willus08.bankapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class DataStorage {

    @PrimaryKey
    @NonNull
    String password;
    String encryptedAccountNumber;
    String decryptingCipher;

    public DataStorage(String password, String encryptedAccountNumber, String decryptingCipher) {
        this.password = password;
        this.encryptedAccountNumber = encryptedAccountNumber;
        this.decryptingCipher = decryptingCipher;
    }

    public String getEncryptedAccountNumber() {
        return encryptedAccountNumber;
    }

    public String getDecryptingCipher() {
        return decryptingCipher;
    }

    @NonNull
    public String getPassword() {
        return password;
    }
}

