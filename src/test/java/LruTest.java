import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.baryshev.kirill.algorithms.AlgorithmLRU;
import ru.baryshev.kirill.MySuperCache;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class LruTest {

    @Autowired
    @Qualifier("cacheLRU")
    MySuperCache cache;

    @Value("${cache.cacheSize}")
    Integer expectedSize;

    @Test
    public void testNameOfAlgorithm() {
        Assert.assertEquals(AlgorithmLRU.class.getSimpleName(), cache.getAlgorithmName());
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
