package com.GoFit.Training.Interceptor;

import com.GoFit.SystemEvent.*;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RefreshScope
@RequiredArgsConstructor
public class GRPCService {
    @Value(value = "${address: localhost}")
    private String address;

    @Value("${port: 0}")
    private int port;
    private final EurekaClient eurekaClient;

    public void save(String eventType,String resourceName,String responseType){
        try{

          /*  ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090)
                    .usePlaintext()
                    .build();*/

            InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("systemevent-service", false);
            //ManagedChannel channel = ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(),instanceInfo.getPort() ).usePlaintext().build();

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090 ).usePlaintext().build();
            EventServiceGrpc.EventServiceBlockingStub stub=EventServiceGrpc.newBlockingStub(channel);
            String newTime=new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss").format(new Date()).toString();
            List<String> ms= List.of(resourceName.split("/"));
            System.out.println(ms.get(1));
            EventResponse response=stub.save(EventRequest.newBuilder()
                    .setTimestamp(newTime)
                    .setMicroservice(ms.get(1))
                    .setActionType(eventType)
                    .setResourceName(resourceName)
                    .setResponseType(responseType)
                    .build());
            System.out.println("timestamp: "+newTime+", microservice: "+ms.get(1)+", actionType: "+eventType+", resourceName: "+resourceName+", responseType "+
                    responseType);
        }
        catch(Exception ignored){

        }
    }
}