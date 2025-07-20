package ru.yandex.practicum.eror.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

/**
 * Ответ об ошибке
 */
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    /**
     * Причина ошибки
     */
    Throwable cause;

    /**
     * Трасировка стека
     */
    StackTraceElement[] stackTrace;

    /**
     * Ответ сервера на HTTP запрос
     */
    HttpStatus httpStatus;

    /**
     * Сообщение пользователю
     */
    String userMessage;

    /**
     * Сообщение
     */
    String message;

    /**
     * Бросаемые ошибки
     */
    Throwable[] suppressed;

    /**
     * Строка из исполняемого файла
     */
    String localizedMessage;
}
