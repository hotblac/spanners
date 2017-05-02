package org.dontpanic.spanners.springmvc.rules;

import org.junit.rules.ExternalResource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * A JUnit Rule that sets up Sys.out for testing
 *
 * Created by stevie on 02/05/17.
 */
public class SystemOutResource extends ExternalResource {

    private PrintStream sysOut;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Override
    protected void before() throws Throwable {
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @Override
    protected void after() {
        System.setOut(sysOut);
    }

    public String asString() {
        return outContent.toString();
    }
}
