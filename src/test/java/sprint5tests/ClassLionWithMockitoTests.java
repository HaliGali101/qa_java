package sprint5tests;

import com.example.Feline;
import com.example.Lion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ClassLionWithMockitoTests {

    @Mock
    Feline feline;

    @Test
    public void getKittensMethodWithoutMockTest() throws Exception {
        Feline feline = new Feline();
        Lion lion = new Lion("Самец", feline);
        assertEquals(1, lion.getKittens());
    }

    @Test
    public void getKittensMethodWithMockTest() throws Exception {
        Lion lion = new Lion("Самец", feline);
        Mockito.when(feline.getKittens()).thenReturn(5);
        Lion spyLion = Mockito.spy(lion);
        assertEquals(5, spyLion.getKittens());
        Mockito.verify(spyLion, Mockito.times(1)).getKittens();
    }

    @Test
    public void doesHaveManeMethodWithMockTest() throws Exception {
        Lion lion = new Lion("Самец", feline);
        Lion spyLion = Mockito.spy(lion);
        Mockito.when(spyLion.doesHaveMane()).thenReturn(true);
        assertTrue(spyLion.doesHaveMane());

        Mockito.when(spyLion.doesHaveMane()).thenReturn(false);
        assertFalse(spyLion.doesHaveMane());

        Mockito.verify(spyLion, Mockito.times(2)).doesHaveMane();
    }

    @Test
    public void getFoodMethodWithMockTestPositive() throws Exception {

        List<String> expectedList = List.of("Животные", "Птицы", "Рыба");

        Lion lion = new Lion("Самец", feline);
        Lion spyLion = Mockito.spy(lion);
        Mockito.when(feline.getFood(Mockito.anyString())).thenReturn(expectedList);

        assertEquals(expectedList, spyLion.getFood());

        Mockito.verify(spyLion, Mockito.times(1)).getFood();
    }

    @Test
    public void getFoodMethodWithMockTestNegative() throws Exception {

        String errorMessage = "Ошибочка";

        Lion spyLion = Mockito.mock(Lion.class);

        Mockito.when(spyLion.getFood()).thenThrow(new Exception(errorMessage));
        Exception exception = assertThrows(Exception.class,
                spyLion::getFood
        );

        assertEquals(errorMessage, exception.getMessage());

        Mockito.verify(spyLion, Mockito.times(1)).getFood();
    }

    @Test(expected = Exception.class)
    public void initClassWithThrowsException() throws Exception {
        new Lion("Гусь", new Feline());
    }
}
