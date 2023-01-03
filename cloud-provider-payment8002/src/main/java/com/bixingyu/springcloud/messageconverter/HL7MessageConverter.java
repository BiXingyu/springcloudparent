package com.bixingyu.springcloud.messageconverter;

import com.bixingyu.springcloud.entities.CommonResult;
import com.bixingyu.springcloud.entities.Payment;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BiXingyu
 * @create 2022-12-21 23:16
 */
public class HL7MessageConverter implements HttpMessageConverter<CommonResult<Payment>> {
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        if(clazz.isAssignableFrom(CommonResult.class)){

            return true;
        }else
            return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        ArrayList<MediaType> mediaTypes = new ArrayList<>();
        MediaType mediaType = MediaType.parseMediaType("application/hl7;charset=utf-8");
        MediaType mediaType1 = MediaType.parseMediaType("application/hl7");
        mediaTypes.add(mediaType);
        mediaTypes.add(mediaType1);
        return mediaTypes;

    }

    @Override
    public CommonResult<Payment> read(Class<? extends CommonResult<Payment>> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }


    @Override
    public void write(CommonResult commonResult, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Integer code = commonResult.getCode();
        String message = commonResult.getMessage();
        Payment payment = (Payment)commonResult.getData();

        String s = new String("消息状态码：" + code + "消息：" + message + "data：" + payment.toString());
        OutputStream body = outputMessage.getBody();
        body.write(s.getBytes(StandardCharsets.UTF_8));
        body.flush();
        body.close();

    }
}
