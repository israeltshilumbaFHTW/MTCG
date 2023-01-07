package at.fhtw.sampleapp.db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbInitTest {

    @Test
    void initDB() {
        DbInit dbInit = new DbInit();
        dbInit.initDB();
    }
}