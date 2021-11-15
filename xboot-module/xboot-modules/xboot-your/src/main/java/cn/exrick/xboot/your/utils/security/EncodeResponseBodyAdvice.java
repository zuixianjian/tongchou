package cn.exrick.xboot.your.utils.security;

import cn.exrick.xboot.your.utils.CommonUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import joptsimple.internal.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 返回数据加密
 * 
 */
@SuppressWarnings("rawtypes")
@Slf4j
@RestControllerAdvice
public class EncodeResponseBodyAdvice implements ResponseBodyAdvice {
 
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }
    private static final String DEFAULT_DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    private static final String DEFAULT_DATE_FORMATTER = "yyyy-MM-dd";

    private static final String DEFAULT_TIME_FORMATTER = "HH:mm:ss";
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        boolean encode = false;
        if (methodParameter.getMethod().isAnnotationPresent(SecretAnnotation.class)) {
            //获取注解配置的包含和去除字段
        	SecretAnnotation serializedField = methodParameter.getMethodAnnotation(SecretAnnotation.class);
            //出参是否需要加密
            encode = serializedField.encode();
        }
        /**
         * 加密开始
         */
        if (encode) {
            log.info("对接口名为【" + methodParameter.getMethod().getName() + "】返回数据进行加密");
            ObjectMapper objectMapper = new ObjectMapper();

            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addDeserializer(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                @Override
                public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                        throws IOException, JsonProcessingException {
                    String value = jsonParser.getValueAsString();
                    if (Strings.isNullOrEmpty(value)) {
                        return null;
                    }
                    return LocalDateTime.parse(jsonParser.getValueAsString(), DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMATTER));
                }
            });
            javaTimeModule.addSerializer(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                @Override
                public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                    if (Objects.isNull(localDateTime)) {
                        jsonGenerator.writeString("");
                    }
                    jsonGenerator.writeString(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMATTER).format(localDateTime));
                }
            });
            javaTimeModule.addDeserializer(LocalDate.class, new JsonDeserializer<LocalDate>() {
                @Override
                public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                        throws IOException, JsonProcessingException {
                    if(Strings.isNullOrEmpty(jsonParser.getValueAsString())){
                        return null;
                    }
                    return LocalDate.parse(jsonParser.getValueAsString(), DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMATTER));
                }
            });
            javaTimeModule.addSerializer(LocalDate.class, new JsonSerializer<LocalDate>() {
                @Override
                public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                    if (Objects.isNull(localDate)) {
                        jsonGenerator.writeString("");
                    }else {
                        jsonGenerator.writeString(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMATTER).format(localDate));
                    }
                }
            });
            javaTimeModule.addDeserializer(LocalTime.class, new JsonDeserializer<LocalTime>() {
                @Override
                public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                        throws IOException, JsonProcessingException {
                    if(Strings.isNullOrEmpty(jsonParser.getValueAsString())){
                        return null;
                    }
                    return LocalTime.parse(jsonParser.getValueAsString(), DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMATTER));
                }
            });
            javaTimeModule.addSerializer(LocalTime.class, new JsonSerializer<LocalTime>() {
                @Override
                public void serialize(LocalTime localTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                    if(Objects.isNull(localTime)){
                        jsonGenerator.writeString("");
                    }
                    jsonGenerator.writeString(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMATTER).format(localTime));
                }
            });
            objectMapper.registerModules(javaTimeModule);
            try {
                String result = objectMapper.writeValueAsString(body);
                serverHttpResponse.getHeaders().add("use_key", CommonUtils.getRandomNickname(8));
                log.info("加密返回数据 :【" + AESUtil.encrypt(result) + "】");
                return AESUtil.encrypt(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return body;
    }
}
