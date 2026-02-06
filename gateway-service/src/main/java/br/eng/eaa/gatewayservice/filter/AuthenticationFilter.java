package br.eng.eaa.gatewayservice.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final WebClient.Builder webClientBuilder;

    public AuthenticationFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    public static class Config {
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token não encontrado"));
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            return webClientBuilder.build()
                    .get()
                    .uri("http://eaa-security-service/login/validate")
                    .header(HttpHeaders.AUTHORIZATION, authHeader)
                    .retrieve()
                    // Se o status for 2xx, continua. Se for 4xx ou 5xx, lança erro.
                    .bodyToMono(Void.class)
                    .then(chain.filter(exchange))
                    .onErrorResume(ex -> {
                        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autorizado"));
                    });
        };
    }
}