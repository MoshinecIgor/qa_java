import com.example.Cat;
import com.example.Feline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatTest {

    Feline felineMock;
    Cat cat;

    @BeforeEach
    void setUp() throws Exception {
        // Создаем мок для Feline
        felineMock = mock(Feline.class);
        // Задаем поведение мока
        when(felineMock.eatMeat()).thenReturn(Arrays.asList("Мясо", "Рыба"));
        // Создаем объект Cat с использованием мока Feline
        cat = new Cat(felineMock);
    }

    @Test
    void testGetSound() {
        // Проверка, что метод getSound возвращает "Мяу"
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    void testGetFood() throws Exception {
        // Проверка, что метод getFood возвращает значение, которое мы задали в моке
        List<String> food = cat.getFood();
        assertEquals(Arrays.asList("Мясо", "Рыба"), food);
    }
}
