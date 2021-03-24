package ru.baryshev.kirill;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import ru.baryshev.kirill.algorithms.Algorithm;
import ru.baryshev.kirill.algorithms.AlgorithmLFU;
import ru.baryshev.kirill.algorithms.AlgorithmLRU;

public class AlgorithmFactory implements FactoryBean<Algorithm> {

    @Value("${cache.strategy}")
    private String algorithmType;

    @Override
    public Algorithm getObject() throws Exception {
        return RemovalAlgorithmsEnum.LFU.getId().equals(algorithmType) ? new AlgorithmLFU() : new AlgorithmLRU();
    }

    @Override
    public Class<?> getObjectType() {
        return Algorithm.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
