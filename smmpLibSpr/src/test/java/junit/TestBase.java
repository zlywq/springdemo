package junit;

import java.io.FileNotFoundException;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:test-root-context.xml")
public class TestBase extends AbstractJUnit4SpringContextTests {
	public final static String springXmlConfigFilePath = "classpath:spring/root-context.xml";

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	

}
