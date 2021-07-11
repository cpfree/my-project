package cn.cpf.web.base.util.cast;

import cn.cpf.web.base.lang.base.PostDto;
import cn.cpf.web.base.util.exception.PostException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

/**
 * <b>Description : </b> Json工具类
 *
 * @author CPF
 * @date 2019/7/24 18:06
 **/
@Slf4j
@NoArgsConstructor
public class JsonUtils {

    /**
     * jackson 转换对象, 线程安全, 可以单例
     */
    public static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T convertJson2Object(String json, Class<T> clazz) throws IOException {
        if (json == null) {
            return null;
        }
        return mapper.readValue(json, clazz);
    }

    public static <T> T convertJson2Object(String json, Class<T> clazz, String throwMsg) {
        Objects.requireNonNull(json, "convertJson2Object 参数 json 为空");
        Objects.requireNonNull(clazz, "convertJson2Object 参数 clazz 为空");
        try {
            return convertJson2Object(json, clazz);
        } catch (Exception e) {
            log.error(throwMsg, e);
            throw new PostException(throwMsg);
        }
    }

    public static <T> PostDto<T> convertJsonToPostDto(String json, Class<T> beanClass) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(PostDto.class, beanClass);
        PostDto<T> postDto;
        try {
            postDto = mapper.readValue(json, javaType);
        } catch (IOException e) {
            String throwMsg = "convertJsonToPostDto 转换异常!, json : " + json + ", beanClass : " + beanClass.getName();
            log.error(throwMsg, e);
            throw new PostException("PostDto转换异常");
        }
        return postDto;
    }

    public static String convertObject2Json(Object obj) throws IOException {
        if (obj == null) {
            return null;
        }
        return mapper.writeValueAsString(obj);
    }

    public static String convertObject2Json(Object obj, String throwMsg) {
        try {
            return convertObject2Json(obj);
        } catch (Exception e) {
            log.error("convertObject2Json 异常", e);
            throw new PostException(throwMsg);
        }
    }

}
