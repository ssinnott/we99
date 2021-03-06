package edu.harvard.we99.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static edu.harvard.we99.test.BaseFixture.assertJsonEquals;
import static edu.harvard.we99.test.BaseFixture.load;
import static org.assertj.core.util.Arrays.array;

/**
 * Tests for the functions I'm using to scrub the JSON
 *
 * @author mford
 */
@RunWith(Parameterized.class)
public class ScrubberTest {

    @Parameterized.Parameters
    public static List<Object[]> params() throws Exception {
        List<Object[]> params = new ArrayList<>();

        params.add(
                array(  load("/ScrubberTest/input.json"),
                        load("/ScrubberTest/pk-expected.json"),
                        Scrubbers.pkey)
        );

        params.add(
                array(  load("/ScrubberTest/input.json"),
                        load("/ScrubberTest/uuid-expected.json"),
                        Scrubbers.uuid)
        );

        params.add(
                array(load("/ScrubberTest/input.json"),
                        load("/ScrubberTest/composed-expected.json"),
                        Scrubbers.uuid.andThen(Scrubbers.pkey))
        );

        return params;
    }

    private final String input;
    private final String expected;
    private final Function<String,String> function;

    public ScrubberTest(String input, String expected, Function<String,String> function) {
        this.input = input;
        this.expected = expected;
        this.function = function;
    }

    @Test
    public void test() throws Exception {

        String scrubbed = function.apply(input);
        assertJsonEquals(expected, scrubbed);
        assertJsonEquals(expected, input, function);
    }
}
