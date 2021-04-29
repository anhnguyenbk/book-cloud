package com.anhnguyen.bookcloud.exception;

import com.anhnguyen.bookcloud.api.model.ApiError;
import com.anhnguyen.bookcloud.api.model.Fault;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The exception handler for converting exception to ApiError response
 */
@ControllerAdvice
@Slf4j
public class BookCloudExceptionHandler {
    /**
     * Handle generic exception
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<ApiError> handleException(Exception exception) {
        log.error("An unexpected error has occurred:", exception);
        final ApiError apiError = new ApiError().addFaultsItem(new Fault()
                .errorCode(1000) // Example error code
                .message(exception.getMessage())
                .debugMessage(ExceptionUtils.getStackTrace(exception))
                .externalMessage(exception.getMessage()));

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiError);
    }

    /**
     * Handle EntityNotFoundException exception
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseBody
    public ResponseEntity<ApiError> handleEntityNotFoundException(final EntityNotFoundException exception) {
        log.info("Not found exception: ", exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null);
    }
}
