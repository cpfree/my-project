package cn.cpf.web.service.mod.spring.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.GenericConversionService;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/16 22:56
 **/
public class ItyConversionService implements ConversionService {

    @Autowired
    private GenericConversionService genericConversionService;
    /**
     * 用于存放转换器集合
     */
    private Set<?> converters;

    /**
     * 通过converters属性我们可以接收需要注册的Converter、ConverterFactory和GenericConverter，在converters属性设置完成之后afterPropertiesSet方法会被调用，在这个方法里面我们把接收到的converters都注册到注入的GenericConversionService中
     */
    @PostConstruct
    public void afterPropertiesSet() {
        if (converters != null) {
            for (Object converter : converters) {
                if (converter instanceof Converter<?, ?>) {
                    genericConversionService.addConverter((Converter<?, ?>)converter);
                } else if (converter instanceof ConverterFactory<?, ?>) {
                    genericConversionService.addConverterFactory((ConverterFactory<?, ?>)converter);
                } else if (converter instanceof GenericConverter) {
                    genericConversionService.addConverter((GenericConverter)converter);
                }
            }
        }
    }

    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        return genericConversionService.canConvert(sourceType, targetType);
    }

    @Override
    public boolean canConvert(TypeDescriptor sourceType,
                              TypeDescriptor targetType) {
        return genericConversionService.canConvert(sourceType, targetType);
    }

    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        return genericConversionService.convert(source, targetType);
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType,
                          TypeDescriptor targetType) {
        return genericConversionService.convert(source, sourceType, targetType);
    }

    public Set<?> getConverters() {
        return converters;
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }


}
