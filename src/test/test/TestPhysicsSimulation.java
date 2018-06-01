package test;

import org.junit.Test;
import static org.junit.Assert.*;

import logic.PhysicsSimulation;

// Christopher Scarborough
public class TestPhysicsSimulation {

    @Test
    public void testUpdate() {

        // Loop Test
        //
        // The loops in update() run a fixed number of times based on the fixed number of PhysicsObjects created.
        // It is not possible for them to not run, run once, or run n - 1 times.
        // As such, we have only one test case for the loops in this method.

        PhysicsSimulation p = new PhysicsSimulation(1.0/60.0, 1000, 600, 20);

        p.update();

    }


}
