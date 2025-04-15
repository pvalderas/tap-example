package es.upv.pros.pvalderas.rabbitmq;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQManager {

    private Channel channel;
    private final String QUEUE="WoTExecutions";

    public static RabbitMQManager instance;
    public static RabbitMQManager getCurrentInstance(){
        if(instance==null) instance=new RabbitMQManager();
        return instance;
    }

    private RabbitMQManager(){
        connectRabbit();
    }
    
    private void connectRabbit(){
    	/*
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
        if(password!=null) factory.setPassword(user);*/
        
        
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
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

 /*   private Properties props=null;
	private Properties getProps(){
        if(props==null){
            String resourceName = "application.properties"; // could also be a constant
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            Properties props = new Properties();
            InputStream resourceStream = loader.getResourceAsStream(resourceName);
            try {
                props.load(resourceStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return props;
    }*/
}
