package ru.alfabank.api.eco.emuls.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@SuppressWarnings({"checkstyle:all"})
public class WSPostController {
    /**
    private final GetAllRights getAllRights;
    private final GetRolesRights getRolesRights;
    private final SomeMock someMock;
     */

    /** пример мока, для которого задержка работает через аннотацию
    @ExecutionTimeControl(mockClass = SomeMock.class)
    @RequestMapping(value = "url")
    @Timed(
            percentiles = {0.5, 0.9, 0.95, 0.99}
    )
    public String mockWSAccountClickPayment14(@RequestBody String requestBody, HttpServletRequest request) {
        log.info("Request {} from {} with body \n {}", request.getMethod(), request.getRequestURI(), requestBody);
        String response = someMock.mock(requestBody);
        log.info("Response: \n {}", response);
        return response;
    }


    пример мока, для которого задержка работает через класс TimeManager.
    Разница - аннотацию нельзя применять где возвращаются несколько значений

    @RequestMapping(value = "url")
    @Timed(
            percentiles = {0.5, 0.9, 0.95, 0.99}
    )
    public String mockWSCustomerRightsNib10(@RequestBody String requestBody, HttpServletRequest request) {
        long tStart = System.currentTimeMillis();
        log.info("Request {} from {} with body \n {}", request.getMethod(), request.getRequestURI(), requestBody);
        String response;
        Wrapper wrapper;
        if (requestBody.contains("GetAllRights")) {
            response = getAllRights.mock(requestBody);
            wrapper = new Wrapper(response, getAllRights);
        } else if (requestBody.contains("GetRolesRights")) {
            response = getRolesRights.mock(requestBody);
            wrapper = new Wrapper(response, getRolesRights);
        } else {
            return new NoSuchMockException("The request does not contain strings:" +
                    " GetAllRights or GetRolesRights or GetAuthorizedPersons").toString();
        }
        log.info("Response: \n {}", response);
        TimeManager.sleep(wrapper.getMock(), tStart);
        return response;
    }
    */


}
