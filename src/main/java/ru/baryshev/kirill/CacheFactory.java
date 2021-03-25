package ru.baryshev.kirill;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;

@Log4j
@AllArgsConstructor
public class CacheFactory implements FactoryBean<Cache> {

    private String algorithmType;

    private Integer maxSize;

    @Override
    public Cache getObject() throws Exception {
        switch (algorithmType.toUpperCase()) {
            case "LFU":
                return new CacheLFU(maxSize);
            case "LRU":
                return new CacheLRU(maxSize);
            default:
                log.error("Не возможно создать кеш с типом алгоритма: " + algorithmType);
                throw new IllegalArgumentException("Неверный тип алгоритма" + algorithmType);
        }
    }

    @Override
    public Class<?> getObjectType() {
        return Cache.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
