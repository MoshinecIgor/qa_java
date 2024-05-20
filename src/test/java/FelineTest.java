import com.example.Feline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FelineTest {
    Feline feline;

    @BeforeEach
    void setUp() {
        feline = Mockito.spy(Feline.class);
    }

    @Test
    void testEatMeat() throws Exception {
        // Задаем поведение мока для метода getFood
        doReturn(Arrays.asList("Мясо", "Рыба")).when(feline).getFood("Хищник");
        // Проверяем, что eatMeat возвращает правильный результат
        List<String> food = feline.eatMeat();
        assertEquals(Arrays.asList("Мясо", "Рыба"), food);
    }

    @Test
    void testGetFamily() {
        // Проверяем, что метод getFamily возвращает "Кошачьи"
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    void testGetKittensDefault() {
        // Проверяем, что метод getKittens без параметров возвращает 1
        assertEquals(1, feline.getKittens());
    }

    @Test
    void testGetKittensWithParam() {
        // Проверяем, что метод getKittens с параметром возвращает значение параметра
        assertEquals(3, feline.getKittens(3));
    }

    @Test
    void testGetFood() throws Exception {
        // Задаем повдение мока для метода getFood в классе Animal
        doReturn(Arrays.asList("Мясо", "Рыба")).when(feline).getFood("Хищник");
        // Проверяем, что метод getFood возвращает правильный результат
        List<String> food = feline.getFood("Хищник");
        assertEquals(Arrays.asList("Мясо", "Рыба"), food);
    }

    @Test
    void testGetFoodWithSuper() throws Exception {
        try {
            // Задаем поведение для super.getFood
            List<String> food = feline.getFood("Травоядное");
            assertEquals(Arrays.asList("Трава", "Различные растения"), food);
        } catch (Exception e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }
}
