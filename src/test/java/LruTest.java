import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.baryshev.kirill.AlgorithmLRU;
import ru.baryshev.kirill.MySuperCache;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class LruTest {

    @Autowired
    @Qualifier("cacheLRU")
    MySuperCache cache;

    @Value("${cache.cacheSize}")
    Integer expectedSize;

    @Test
    public void testNameOfAlgorithm() {
        Assert.assertEquals(cache.getAlgorithmName(), AlgorithmLRU.class.getSimpleName());
    }
}
