package com.ictm2n2.resources;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BacktrackingTest {

    @Test
    public void HoeveelVan() {
        Componenten componenten = new Componenten();
        DatabaseServer goedkoopsteDb = componenten.dbServers.get(0);
        Configuratie c = new Configuratie();
        Backtracking bt = new Backtracking();
        c.voegToeComponent(goedkoopsteDb);
        assertEquals(bt.hoeveelVan(goedkoopsteDb, c), 1);
    }

}
