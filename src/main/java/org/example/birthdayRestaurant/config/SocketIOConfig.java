package org.example.birthdayRestaurant.config;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class SocketIOConfig {
    @Value("${socketio.host}")
    private String host;

    @Value("${socketio.port}")
    private Integer port;

    @Bean
    public SocketIOServer socketIOServer() {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setSocketConfig(socketConfig);
        config.setHostname(host);
        config.setPort(port);
        /*config.setBossThreads(bossCount);
        config.setWorkerThreads(workCount);
        config.setAllowCustomRequests(allowCustomRequests);
        config.setUpgradeTimeout(upgradeTimeout);
        config.setPingTimeout(pingTimeout);
        config.setPingInterval(pingInterval);*/

        SocketIOServer socketIOServer = new SocketIOServer(config);
        //SocketIONamespace socketIONamespace = socketIOServer.addNamespace("barrage");
        socketIOServer.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println();
            }
        });

        socketIOServer.addEventListener("init", Map.class, new DataListener<Map>() {
            @Override
            public void onData(SocketIOClient client, Map data, AckRequest ackSender) throws Exception {
                client.joinRoom("barrage");
            }
        });
        return socketIOServer;
    }

    @Bean
    public SocketIONamespace socketIONamespace(SocketIOServer socketIOServer) {
        SocketIONamespace socketIONamespace = socketIOServer.addNamespace("/barrage");
        socketIONamespace.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println();
            }
        });
        socketIONamespace.addEventListener("init", Map.class, new DataListener<Map>() {
            @Override
            public void onData(SocketIOClient client, Map data, AckRequest ackSender) throws Exception {
                client.joinRoom("barrage");
            }
        });

        return socketIONamespace;
    }
}
