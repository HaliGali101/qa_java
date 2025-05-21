package sprint5tests;

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
public class ClassFelineWithMockitoTests {

    @Mock
    Feline mockFeline;

    @Test
    public void eatMeatMethodTestPositive() throws Exception {

        List<String> expectedList = List.of("Животные", "Птицы", "Рыба");

        Feline spyFeline = Mockito.spy(Feline.class);
        List<String> actualList = spyFeline.eatMeat();

        assertEquals(
                "Возвращаемое значение отлично от ожидаемого",
                expectedList,
                actualList
        );
        Mockito.verify(spyFeline, Mockito.times(1)).eatMeat();
    }

    @Test
    public void eatMeatMethodTestNegative() throws Exception {

        String errorMessage  = "Ожидаемая ошибка";
        Mockito.when(mockFeline.eatMeat()).thenThrow(new Exception(errorMessage));

        Exception exception = assertThrows(Exception.class, () -> mockFeline.eatMeat());

        assertEquals(
                "Текст ошибки отличен от ожидаемого.\nОжидаем текст ошибки: " + errorMessage,
                errorMessage,
                exception.getMessage()
        );
        Mockito.verify(mockFeline, Mockito.times(1)).eatMeat();
    }

    @Test
    public void getFamilyMethodTest() {

        String expected = "Кошачьи";

        Feline spyFeline = Mockito.spy(Feline.class);
        String actual = spyFeline.getFamily();

        assertEquals(
                "Возвращаемое значение отлично от ожидаемого",
                expected,
                actual
        );

        Mockito.verify(spyFeline, Mockito.times(1)).getFamily();
    }

    @Test
    public void getKittensMethodWithoutAttributes() {
        Feline spyFeline = Mockito.spy(Feline.class);
        assertEquals(
                "Актуальное значение должно быть: 1",
                1,
                spyFeline.getKittens()
        );

        spyFeline.getKittens();
        Mockito.verify(spyFeline, Mockito.times(2)).getKittens();

    }

    @Test
    public void getKittensMethodWithAttributes() {

        Feline spyFeline = Mockito.spy(Feline.class);
        assertEquals(
                "Актуальное значение должно быть: 13",
                13,
                spyFeline.getKittens(13)
        );

        spyFeline.getKittens(-5);
        spyFeline.getKittens(0);
        Mockito.verify(spyFeline, Mockito.times(3)).getKittens(Mockito.anyInt());


    }
}
