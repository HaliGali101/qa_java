package sprint5tests;

import com.example.Feline;
import com.example.Lion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ClassLionParameterizedTests {

    String arg;
    boolean hasMane;

    public ClassLionParameterizedTests(String arg, boolean hasMane) {
        this.arg = arg;
        this.hasMane = hasMane;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {

        return new Object[][] {
                { "Самец", true},
                { "Самка", false}
        };
    }

    @Test()
    public void getFoodMethodTestWithParameters() throws Exception {
        Feline feline = new Feline();
        Lion lion = new Lion(arg, feline);
        assertEquals(hasMane, lion.doesHaveMane());
    }
}
