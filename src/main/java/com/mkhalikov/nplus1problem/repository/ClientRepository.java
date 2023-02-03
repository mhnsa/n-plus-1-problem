package com.mkhalikov.nplus1problem.repository;

import com.mkhalikov.nplus1problem.model.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    /*Первый вариант решения, с помощью данной аннотации
        @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "emailAddresses")*/
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "client_entity-graph")
    List<Client> findByFullNameContaining(String userName);

}