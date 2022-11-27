package com.microservice.eurekaclient.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.eurekaclient.entity.Server;

@Repository
public interface ServerRepository extends JpaRepository<Server, String> {
}
