package com.example.bloodonate;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Test
    public void onCreate() {
    }

    @Test
    public void onActivityResult() {
        //assertEquals( "acayro@unsa.edu.pe", FirebaseAuth.getInstance().createUserWithEmailAndPassword("acayro@unsa.edu.pe","1234"));
        //assertEquals("jcarpio@gmail.com", FirebaseAuth.getInstance().createUserWithEmailAndPassword("wolin@unsa.edu","123456789"));
    }

    private void assertNotEquals(Task<AuthResult> userWithEmailAndPassword) {
        String User = "jcarpio@gmail.com";
        String Pass = "12345";
        //userWithEmailAndPassword = User + Pass;

    }
}