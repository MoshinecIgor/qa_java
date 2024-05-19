import com.example.FelineInterface;
import com.example.Lion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LionParameterizedTest {

    FelineInterface felineMock; // Объект мока для интерфейса FelineInterface

    @BeforeEach
    void setUp() {
        felineMock = mock(FelineInterface.class);
        try {
            // Настройка мока для возврата списка еды при вызове метода getFood с параметром "Хищник"
            when(felineMock.getFood("Хищник")).thenReturn(Arrays.asList("Мясо", "Рыба"));
        } catch (Exception e) {
            // Если произошла ошибка при настройке мока, тест должен завершиться с ошибкой
            fail("Setup should not throw exception: " + e.getMessage());
        }
        // Настройка мока для возврата количества котят
        when(felineMock.getKittens()).thenReturn(3);
    }

    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false",
            "Unknown, exception",
            "'', exception"
    })
    void testLion(String sex, String expectedResult) {
        if ("exception".equals(expectedResult)) {
            // Проверка, что при недопустимых значениях пола появляется исключение
            assertThrows(Exception.class, () -> new Lion(sex, felineMock));
        } else {
            try {
                // проверка наличия гривы
                Lion lion = new Lion(sex, felineMock);
                assertEquals(Boolean.parseBoolean(expectedResult), lion.doesHaveMane());
            } catch (Exception e) {
                // если появится исключение когда его не должно быть, то тест не пройдет
                fail("Exception should not have been thrown");
            }
        }
    }

    @ParameterizedTest
    @NullAndEmptySource
        // Тестирование поведения при пустом или null значении пола
    void testLionWithNullOrEmpty(String sex) {
        // проверяем, что при пустом значении пола появиттся исключение
        assertThrows(Exception.class, () -> new Lion(sex, felineMock));
    }
}