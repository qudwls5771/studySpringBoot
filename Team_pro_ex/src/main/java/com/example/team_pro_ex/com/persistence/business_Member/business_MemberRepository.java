package com.example.team_pro_ex.com.persistence.business_Member;

import com.example.team_pro_ex.com.Entity.business_Member.business_Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface business_MemberRepository extends JpaRepository<business_Member, String> {
}
