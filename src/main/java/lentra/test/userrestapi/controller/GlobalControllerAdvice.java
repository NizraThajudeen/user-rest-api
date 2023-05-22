package lentra.test.userrestapi.controller;

//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex,WebRequest request) {
//
//        logger.info("at handleConstraintViolationException: GlobalControllerAdvice");
//        logger.error(ex.getMessage(), ex);
//
//
//        String requestSent = request.getDescription(false);
//        //process the errors bind with exceptions
//
//        return new ResponseEntity<String>("something to identify", HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {

        logger.info("at handleMethodArgumentNotValidException: GlobalControllerAdvice");
        logger.error(ex.getMessage(), ex);

        //get object containing all errors
        BindingResult result = ex.getBindingResult();
        //get the errors into a list
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();

        String error="";
        int count=0;

        //loop through the list of errors
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {

            if(count > 0){
                //get the default message of each error
                error = error+", "+ fieldError.getDefaultMessage();
            }
            else{
                error = error+ fieldError.getDefaultMessage();
            }
            count++;

            logger.debug(error);

        }

        String requestSent = request.getDescription(false);


        //process the errors bind with exceptions
        return new ResponseEntity<String>(error, HttpStatus.BAD_REQUEST);
    }
}
