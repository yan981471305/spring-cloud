package demo.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

  /*  @Value("${slack.url}")
    String slackUrl;

    @Value("${slack.channel}")
    String slackChannel;

    @Value("${slack.user}")
    String slackUser;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @EventListener(condition = "#eurekaInstanceCanceledEvent.replication==false")
    public void listenCanceled(EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent){
        String message = String.format("Instance(%s) of service(%s) is DOWN at %s",
                eurekaInstanceCanceledEvent.getServerId(),
                eurekaInstanceCanceledEvent.getAppName(),
                sdf.format(new Date()));
        SlackApi api = new SlackApi(slackUrl);
        api.call(new SlackMessage(slackChannel,slackUser,message));
    }

    @EventListener(condition = "#eurekaInstanceRegisteredEvent.replication==false")
    public void listenRegistry(EurekaInstanceRegisteredEvent eurekaInstanceRegisteredEvent){
        if(eurekaInstanceRegisteredEvent.getInstanceInfo().getStatus() == InstanceInfo.InstanceStatus.UP){
            String message = String.format("Instance(%s) of service(%s) is UP at %s",
                    eurekaInstanceRegisteredEvent.getInstanceInfo().getInstanceId(),
                    eurekaInstanceRegisteredEvent.getInstanceInfo().getAppName(),
                    sdf.format(new Date()));
            SlackApi api = new SlackApi(slackUrl);
            api.call(new SlackMessage(slackChannel,slackUser,message));
        }
    }*/

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
