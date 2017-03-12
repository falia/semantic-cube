package converter;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.jena.atlas.lib.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by borellda on 3/12/2017.
 */
final class StringToListConverterFactory implements ConverterFactory<String, List<String>> {
    /* The Logger */
    private static final Logger log = LoggerFactory.getLogger(StringToListConverterFactory.class);

    public <T extends List<String>> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToListConverter(targetType);
    }

    private final class StringToListConverter<T extends List<String>> implements Converter<String, T> {

        private Class<T> listType;

        public StringToListConverter(Class<T> listType) {
            this.listType = listType;
        }

        public T convert(String source) {
            List<String> list = Arrays.stream(source.split(" ")).collect(Collectors.toList());
            return (T) list;
        }
    }
}
