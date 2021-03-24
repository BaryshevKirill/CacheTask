import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.baryshev.kirill.algorithms.AlgorithmLFU;
import ru.baryshev.kirill.MySuperCache;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class LfuTest {

    @Autowired
    @Qualifier("cacheLFU")
    MySuperCache cache;

    @Value("${cache.cacheSize}")
    Integer expectedSize;

    @Test
    public void testNameOfAlgorithm() {
        Assert.assertEquals(AlgorithmLFU.class.getSimpleName(), cache.getAlgorithmName());
    }

    @Test
    public void testMaxSize() {
        cache.put(1L, "Long 1");
        cache.put(2L, "Long 2");
        cache.put(3L, "Long 3");
        cache.put(4L, "Long 4");
        Assert.assertEquals(cache.currentSize(), expectedSize);
    }

    @Test
    public void testRemove() {
        cache.put(1L, "Long 1");
        cache.put(2L, "Long 2");
        cache.put(3L, "Long 3");
        cache.get(1L);
        cache.get(3L);
        cache.put(4L, "Long 4");
        Assert.assertNull(cache.get(2L));
    }
}
