package sprint5tests;

import com.example.Animal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ClassAnimalParameterizedTests {

    String arg;
    Object expectedObject;

    public ClassAnimalParameterizedTests(String arg, Object expectedObject) {
        this.arg = arg;
        this.expectedObject = expectedObject;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {

        return new Object[][] {
                { "Травоядное", List.of("Трава", "Различные растения")},
                { "Хищник", List.of("Животные", "Птицы", "Рыба")}
        };
    }

    @Test()
    public void getFoodMethodTestWithParameters() throws Exception {
        Animal animal = new Animal();
        assertEquals(expectedObject, animal.getFood(arg));
    }
}
