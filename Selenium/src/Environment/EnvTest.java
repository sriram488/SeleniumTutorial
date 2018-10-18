package Environment;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

public class EnvTest {
Environment env = ConfigFactory.create(Environment.class);
public static final String app_env = System.getenv("APP_ENVIRONMENT");

@Test
public void testenv() {
	ConfigFactory.setProperty("env", app_env);
	env = ConfigFactory.create(Environment.class);
	
	System.out.println(env.APP_URL());
	System.out.println(env.getUserName());
	System.out.println(env.getPassword());

}
}
