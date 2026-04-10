package wolf.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class wolfModelGunTest {
    private Gun gun;

    @BeforeEach
    public void setUp() {
        gun = new Gun();
    }

    @Test
    public void testFire() {
        gun.fire();
        assertTrue(gun.isFiring());
    }

    @Test
    public void testMultipleShots() {
        gun.fire();
        assertTrue(gun.isFiring());
        assertEquals(98, gun.ammoProperty().get());

        gun.fire();
        assertEquals(98, gun.ammoProperty().get());

        for (int i=0; i<46; i++) {
            gun.update();
        }

        gun.fire();
        assertEquals(97, gun.ammoProperty().get());
    }
}
