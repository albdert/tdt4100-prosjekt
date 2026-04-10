package wolf.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.common.Vector2d;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class wolfModelPlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player();
    }

    @Test
    public void testAngleToVectorConversion() {
        Vector2d expected = new Vector2d(Math.cos(0.04*4), Math.sin(0.04*4));
        for (int i=0;i<4;i++) {
            player.lookRight();
        }
        assertEquals(expected.x, player.getDeltaPos().x);
        assertEquals(expected.y, player.getDeltaPos().y);
    }

    @Test
    public void testPlayerDamaged() {
        int expectedArmor = 50;
        int expectedHealth1 = 100;
        int expectedHealth2 = 70;

        player.damaged(50);

        assertEquals(expectedArmor, player.armorProperty().get());
        assertEquals(expectedHealth1, player.healthProperty().get());

        player.damaged(100);
        assertEquals(0, player.armorProperty().get());
        assertEquals(expectedHealth1, player.healthProperty().get());

        player.damaged(30);
        assertEquals(expectedHealth2, player.healthProperty().get());
    }

    @Test
    public void testPlayerMovement() {
        double expectedX1 = 40+4;
        double expectedX2 = 40+4+55;
        int[][] world = {
            {0,1,1,1,1,1},
            {0,0,0,0,0,1},
            {0,0,0,0,0,1},
            {0,0,0,0,0,1}
        };
        assertEquals(40, player.getPos().x);
        assertEquals(40, player.getPos().y);

        for (int i=0; i<4; i++) {
            player.moveForward(world);
        }
        assertEquals(expectedX1, player.getPos().x);

        for (int i=0; i<60; i++) {
            player.moveForward(world);
        }
        assertEquals(expectedX2, player.getPos().x);
    }
}
