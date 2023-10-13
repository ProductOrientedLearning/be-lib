package pol.ecom.micro.shop.lib.exception;
/*
 * This is course Microservice Product Oriented
 * MIT No Attribution

 * Copyright (c) 2023 <Dr.JohnLe & Mr.HaNguyen>

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pol.ecom.micro.shop.lib.constant.MessageCode;
import pol.ecom.micro.shop.lib.dto.response.MessageErrorResponse;
import pol.ecom.micro.shop.lib.util.MessageUtil;

@ControllerAdvice(basePackages = "pol.ecom.micro.shop")
public class ShopExceptionHandler {
    @Autowired
    private MessageUtil messageUtil;

    @ExceptionHandler({ShopException.class})
    public ResponseEntity<MessageErrorResponse> ShopExceptionHandle(ShopException ex){
        if(!ObjectUtils.isEmpty(ex.getCode())){
            HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
            String errorMessage = ex.getMessage() == null ? httpStatus.toString() : ex.getMessage();
            return new ResponseEntity<>(new MessageErrorResponse(ex.getCode(), errorMessage), HttpStatus.BAD_REQUEST);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR, MessageCode.MESSAGE_ERROR_SYSTEM_ERROR.getCode(),
                    messageUtil.getMessage(MessageCode.MESSAGE_ERROR_SYSTEM_ERROR));
        }
    }
    private ResponseEntity<MessageErrorResponse> response(HttpStatus httpStatus, String errorCode, String errorMessage) {
        return new ResponseEntity<>(new MessageErrorResponse(errorCode, errorMessage), httpStatus);
    }

}
