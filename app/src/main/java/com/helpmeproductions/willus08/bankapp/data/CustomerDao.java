package com.helpmeproductions.willus08.bankapp.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.Update;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.helpmeproductions.willus08.bankapp.model.Customer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CustomerDao {
    @Query("select * from customer")
    List<Customer> getCustomers();

    @Query("select * from customer where accountNumber = :accountNumber")
    Customer loadCustomerByAcountNumber(String accountNumber);

    @Insert(onConflict = IGNORE)
    void addCustomer(Customer customer);

    @Update(onConflict = REPLACE)
    void updateCustomer(Customer customer);

     class Converters {
         @TypeConverter
         public static ArrayList<String> fromString(String value) {
             Type listType = new TypeToken<ArrayList<String>>() {}.getType();
             return new Gson().fromJson(value, listType);
         }

         @TypeConverter
         public static String fromArrayList(ArrayList<String> list) {
             Gson gson = new Gson();
             String json = gson.toJson(list);
             return json;
         }
     }
}
