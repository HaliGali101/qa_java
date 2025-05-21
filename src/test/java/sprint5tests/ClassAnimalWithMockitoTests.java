package sprint5tests;

import com.example.Animal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class ClassAnimalWithMockitoTests {

    @Mock
    Animal mockAnimal;

    @Test
    public void getFoodMethodTestPositive() throws Exception {
        mockAnimal.getFood(Mockito.anyString());
        mockAnimal.getFood("Рыба");
        Mockito.verify(mockAnimal, Mockito.times(2)).getFood(Mockito.anyString());
    }

    @Test
    public void getFoodMethodTestWithMockNegative() throws Exception {

        String errorMessage = "Неизвестный вид животного, используйте значение Травоядное или Хищник";

        Mockito.when(mockAnimal.getFood(Mockito.anyString())).thenThrow(new Exception(errorMessage));

        Exception exception = assertThrows(Exception.class,
                () -> mockAnimal.getFood(Mockito.anyString()));

        assertEquals(errorMessage, exception.getMessage());

        Mockito.verify(mockAnimal, Mockito.times(1)).getFood(Mockito.anyString());
    }

    @Test
    public void getFoodMethodTestWithoutMockNegative() {
        Animal animal = new Animal();
        String errorMessage = "Неизвестный вид животного, используйте значение Травоядное или Хищник";

        Exception exception = assertThrows(Exception.class, () -> animal.getFood("Рыбы"));

        assertEquals(
                "Текст ошибки отличен от ожидаемого.\nОжидаем текст ошибки: " + errorMessage,
                errorMessage,
                exception.getMessage()
        );
    }

    @Test
    public void fetFamilyMethodTest() {

        String expectedString = "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";
        Animal spyAnimal = Mockito.spy(Animal.class);
        assertEquals(
                "возвращающееся значение отлично от ожидаемого",
                expectedString,
                spyAnimal.getFamily()
        );

        spyAnimal.getFamily();
        Mockito.verify(spyAnimal, Mockito.times(2)).getFamily();
    }
}
