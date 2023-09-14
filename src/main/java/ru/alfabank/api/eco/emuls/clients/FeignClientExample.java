package ru.alfabank.api.eco.emuls.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Клиент, позволяющий делать рест-запросы.
 * Для этого нужно указать name - имя конкретной апи, url - url до апи.
 * @GetMapping говорит что мы сделаем гет-запрос
 * @PostMapping - пост-запрос
 * В параметрах вышеописанных аннотаций указываем эндпойнт
 * После чего добавляем данный клиент как поле класса и инжектим в него объект из спринг-контекста
 */
@FeignClient(name = "something-api", url = "url/to/api")
public interface FeignClientExample {

    @GetMapping("/endpoint?param1={xpin}&param2={ref}")
    void checkForCompliance(@PathVariable String xpin, @PathVariable String ref);
}
