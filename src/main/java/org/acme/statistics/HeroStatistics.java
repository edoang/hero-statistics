package org.acme.statistics;

import io.quarkus.funqy.Funq;
import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.acme.statistics.hero.GraphQLHeroClient;

import java.time.Instant;

public class HeroStatistics {

    @Inject
    @GraphQLClient("inventory")
    GraphQLHeroClient inventoryClient;

    @Funq
    public Uni<String> getHeroStatistics() {
        return inventoryClient.allHeroes()
                .map(heroes -> ("The Heroes statistics created at %s. " +
                        "Number of available heroes: %d")
                        .formatted(Instant.now(), heroes.size()));
    }
}