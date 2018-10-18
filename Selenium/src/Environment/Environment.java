package Environment;
import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${env}.properties"})
public interface Environment extends Config{

	String APP_URL();
	@Key("username")
	String getUserName();
	@Key("password")
	String getPassword();

}
