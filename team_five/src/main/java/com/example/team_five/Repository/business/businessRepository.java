package com.example.team_five.Repository.business;

import com.example.team_five.Entity.businessMember.businessMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface businessRepository extends JpaRepository<businessMember, String> {
}
