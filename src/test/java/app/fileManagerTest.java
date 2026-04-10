package app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class fileManagerTest {
    private FileManager m;

    @BeforeEach
    public void setUp() {
        m = new FileManager("testscores.txt");
    }

    @Test
    public void testReadFile() {
        Map<String, Map<String,Integer>> scores = m.loadScores();
        assertEquals(null, scores.get("wolf"));

        m.saveScores("wolf", "testplayer", 100);
        scores = m.loadScores();
        assertEquals(1, scores.get("wolf").size());
        assertEquals(100, scores.get("wolf").get("testplayer"));
    }       

    @Test
    public void testOverwriteScore() {
        Map<String, Map<String,Integer>> scores = m.loadScores();

        assertEquals(100, scores.get("wolf").get("testplayer"));
        m.saveScores("wolf", "testplayer", 1000);
        scores = m.loadScores();
        assertEquals(1000, scores.get("wolf").get("testplayer"));
    }     
}
