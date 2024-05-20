import com.example.FelineInterface;
import com.example.Lion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LionTest {

    FelineInterface felineMock; // Мок интерфейса FelineInterface чтобы изолировать тест от класа Feline
    Lion lion;

    @BeforeEach
    void setUp() throws Exception {
        felineMock = mock(FelineInterface.class); // Создал мок
        // настроил мок, чтобы при вызове метода getKittens возвращалось значение 3
        when(felineMock.getKittens()).thenReturn(3);
        // мок, чтобы при вызове метода getFood с параметром "Хищник" возвращался список еды
        when(felineMock.getFood("Хищник")).thenReturn(Arrays.asList("Мясо", "Рыба"));
        lion = new Lion("Самец", felineMock); // Создание объекта Lion с моком и полом "Самец"
    }

    @Test
    void testDoesHaveMane() {
        assertTrue(lion.doesHaveMane()); // Проверка, что у самца льва есть грива
    }

    @Test
    void testGetKittens() {
        assertEquals(3, lion.getKittens()); // Проверка, что метод getKittens возвращает правильное количество котят
    }

    @Test
    void testGetFood() throws Exception {
        List<String> food = lion.getFood(); // Получение списка пищи льва
        assertEquals(Arrays.asList("Мясо", "Рыба"), food); // Проверка, что список еды корректен
    }

    @Test
    void testLionWithFemale() throws Exception {
        Lion femaleLion = new Lion("Самка", felineMock); // создал объект Lion с моком и полом "Самка"
        assertFalse(femaleLion.doesHaveMane()); // Проверка, что у самки льва нет гривы
    }

    @Test
    void testInvalidSex() {
        Exception exception = assertThrows(Exception.class, () -> {
            new Lion("Unknown", felineMock); // Попытка создать объект Lion с недопустимым полом "Unknown"
        });
        // Проверка, что при недопустимом поле выбрасывается исключение с правильным сообщением
        assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
    }
}