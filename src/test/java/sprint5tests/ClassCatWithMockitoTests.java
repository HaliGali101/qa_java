package sprint5tests;

import com.example.Cat;
import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class ClassCatWithMockitoTests {

    @Mock
    Cat mockCat;

    @Mock
    Feline mockFeline;

    @Test
    public void catSoundMethodTest() {

        Cat cat = new Cat(mockFeline);
        Cat spyCat = Mockito.spy(cat);

        assertEquals(
                "Ожидаем возвращение звука: Мяу",
                "Мяу",
                spyCat.getSound());
        Mockito.verify(spyCat, Mockito.times(1)).getSound();
    }

    @Test
    public void getFoodMethodTestPositive() throws Exception {

        Cat cat = new Cat(mockFeline);
        List<String> expectedFood = List.of("Баранина", "Говядина", "Телятина");
        Mockito.when(mockFeline.eatMeat()).thenReturn(expectedFood);
        List<String> actualFood = cat.getFood();

        assertEquals(
                "Должен вернуться список мяса",
                expectedFood,
                actualFood
        );
        Mockito.verify(mockFeline, Mockito.times(1)).eatMeat();
    }

    @Test
    public void getFoodMethodTestNegative() throws Exception {

        String errorMessage  = "Ожидаемая ошибка";
        Mockito.when(mockCat.getFood()).thenThrow(new Exception(errorMessage));

        Exception exception = assertThrows(Exception.class, () -> mockCat.getFood());

        assertEquals(
                "Текст ошибки отличен от ожидаемого.\nОжидаем текст ошибки: " + errorMessage,
                errorMessage,
                exception.getMessage()
        );

        Mockito.verify(mockCat, Mockito.times(1)).getFood();
    }

}
