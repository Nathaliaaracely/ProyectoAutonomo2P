package com.bancodealimentos.controller;

import com.bancodealimentos.model.Donaciones;
import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Controller
public class DonacionesSubscriptionController {

    private final Sinks.Many<Donaciones> sink = Sinks.many().multicast().onBackpressureBuffer();

    public void publicarNuevaDonacion(Donaciones donacion) {
        sink.tryEmitNext(donacion);
    }

    @SubscriptionMapping
    public Publisher<Donaciones> nuevaDonacion() {
        return sink.asFlux();
    }
}
