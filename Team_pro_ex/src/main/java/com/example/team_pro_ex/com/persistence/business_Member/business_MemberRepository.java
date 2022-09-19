package com.example.team_pro_ex.com.persistence.business_Member;

import com.example.team_pro_ex.com.Entity.business_Member.businessMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface business_MemberRepository extends JpaRepository<businessMember, Long> {


    @Query(value = "update business_Member m set m.business_member_password = null, m.business_member_number = null, " +
            "m.business_member_phone_number = null, m.Role = null, m.join_D = null, m.member_join_M = 'N', " +
            "m.join_O = to_date('SYSDATE', 'yyyy.mm.dd') where m.business_Number_Seq = : business_Number_Seq",
            nativeQuery = true)
    businessMember updateDelete(Long business_Number_Seq);


}
