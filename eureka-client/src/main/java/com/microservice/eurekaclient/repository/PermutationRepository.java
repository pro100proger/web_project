package com.microservice.eurekaclient.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.eurekaclient.entity.Permutation;

@Repository
public interface PermutationRepository  extends JpaRepository<Permutation, String> {
    Permutation getPermutationById(String id);
    Optional<List<Permutation>> getAllPermutationsByUserId(String userId);
}
