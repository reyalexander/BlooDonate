package com.example.bloodonate;

import com.example.bloodonate.model.Persona;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SecondActivityTest {
    private Persona tPersona;
    @Test
    public void onCreate() {
    }

    @Before
    public void setUp(){
        tPersona = new Persona();
    }
    @Test
    public void personaNotNull(){
        assertNotNull(tPersona);
    }

    @Test
    public void limpiarCajas() throws Exception{
        assertEquals(null, tPersona.getID());
        assertEquals(null, tPersona.getNombre());
        assertEquals(null, tPersona.getApellidoPaterno());
        assertEquals(null, tPersona.getApellidoMaterno());
        assertEquals(null, tPersona.getDNI());
        assertEquals(null, tPersona.getDireccion());
        assertEquals(null, tPersona.getEmail());
        assertEquals(null, tPersona.getTelefono());
    }

    @Test
    public void agregarDonador() throws Exception{

        assertEquals(null, tPersona.getNombre());
        assertEquals(null, tPersona.getApellidoPaterno());
    }
}