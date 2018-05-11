package com.helpmeproductions.willus08.bankapp;

import com.helpmeproductions.willus08.bankapp.view.activities.login.LoginPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;


@RunWith(RobolectricTestRunner.class)
public class LoginPresenterTests {
    private LoginPresenter presenter = new LoginPresenter();

    @Before
    public void setup(){
    }

    @Test
    public void createUserShouldFail(){
        assertEquals(false, presenter.createNewUser(
                "will",
                "password",
                "passord"));

    }
}
