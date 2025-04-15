package es.upv.pros.pvalderas.context.manager.rabbitmq;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import es.upv.pros.pvalderas.context.manager.ContextManagerAPI;

@Component
public class RabbitMQManager {
	
	@Autowired
	private ContextManagerAPI managerController;

    private Channel channel;
    private final String QUEUE="WoTExecutions";
    
    public RabbitMQManager(){
    	connectRabbit();
    }
    
    private void connectRabbit(){
    	
    	ConnectionFactory factory = new ConnectionFactory();

        String host=getProps().getProperty("rabbit.host");
        String virtualHost=getProps().getProperty("rabbit.virtualhost");
        String port=getProps().getProperty("rabbit.port");
        String user=getProps().getProperty("rabbit.user");
        String password=getProps().getProperty("rabbit.password");

        if(host!=null) factory.setHost(host);
        else factory.setHost("localhost");

        if(virtualHost!=null) factory.setVirtualHost(virtualHost);
        if(port!=null) factory.setPort(Integer.parseInt(port));
        if(user!=null) factory.setUsername(user);
        if(password!=null) factory.setPassword(user);
        
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE, false, false, false, null);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } 
	}

    public void publishMessage(String message){
		try {
            channel.basicPublish("", QUEUE, null, message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addConsumer() throws IOException{
    	Consumer consumer = new DefaultConsumer(channel) {
    		@Override
    		public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
    			try {
    				String message = new String(body, "UTF-8"); 
    				JSONObject execution=new JSONObject(message);
					managerController.addExecution(execution.getString("deviceId"), execution.getString("operation"), execution.getString("user"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
    		}
    	};
    	channel.basicConsume(QUEUE, true, consumer);
    }

    private Properties props;
	private  Properties getProps(){
		if(props==null){
			YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
		    yamlFactory.setResources(new ClassPathResource("application.yml"));
		    props=yamlFactory.getObject();
		}
	    return props;
    }
}
