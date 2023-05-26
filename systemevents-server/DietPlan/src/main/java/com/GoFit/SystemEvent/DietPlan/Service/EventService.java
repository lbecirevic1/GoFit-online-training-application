package com.GoFit.SystemEvent.DietPlan.Service;

import com.GoFit.SystemEvent.DietPlan.Model.Event;
import com.GoFit.SystemEvent.DietPlan.Repository.EventRepository;
import com.GoFit.SystemEvent.EventRequest;
import com.GoFit.SystemEvent.EventResponse;
import com.GoFit.SystemEvent.EventServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class EventService extends EventServiceGrpc.EventServiceImplBase {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public void save(EventRequest request, StreamObserver<EventResponse> responseStreamObserver){
        Event event=new Event();
        event.setTimestamp(request.getTimestamp());
        event.setMicroservice(request.getMicroservice());
        event.setActionType(request.getActionType());
        event.setResourceName(request.getResourceName());
        event.setResponseType(request.getResponseType());
        eventRepository.save(event);

        EventResponse response = EventResponse.newBuilder()
                .setStatus("OK")
                .build();

        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }
}
