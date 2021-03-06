package edu.harvard.we99.services;

import edu.harvard.we99.domain.lists.PlateMaps;
import edu.harvard.we99.test.LogTestRule;
import edu.harvard.we99.util.ClientFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.net.URL;

import static edu.harvard.we99.test.BaseFixture.name;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author markford
 */
public class PlateMapListST {
    @Rule
    public LogTestRule logTestRule = new LogTestRule();

    private static PlateMapService plateMapService;


    @BeforeClass
    public static void init() throws Exception {
        URL url = new URL(WebAppIT.WE99_URL);
        ClientFactory cf = new ClientFactory(url, WebAppIT.WE99_EMAIL, WebAppIT.WE99_PW);

        plateMapService = cf.create(PlateMapService.class);
    }

    @AfterClass
    public static void destroy() throws Exception {
        plateMapService = null;
    }

    @Test
    public void test() throws Exception {
        // import a plate map
        // query based on its size
        String name = name("pm");
        PlateMapClientFixture.upload("/PlateMapListST/input300x300.csv", name);
        PlateMaps plateMaps = plateMapService.listAll(null, null, 500, 600, null);
        assertTrue(plateMaps.getTotalCount()>=1);
        assertThat(plateMaps.getValues()).extracting("name").contains(name);
        plateMaps = plateMapService.listAll(null, null, 500, 600, name);
        assertEquals(1, plateMaps.size());
        // search again for something smaller
        plateMaps = plateMapService.listAll(null, null, 10, 10, null);
        assertThat(plateMaps.getValues()).extracting("name").doesNotContain(name);
    }
}
