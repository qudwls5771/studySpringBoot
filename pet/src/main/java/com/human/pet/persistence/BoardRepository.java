
package com.human.pet.persistence;

import com.human.pet.domain.Board;
import org.springframework.data.repository.CrudRepository;

//CrudRepository 제네릭 타입을 지정
//CrudRepository를 상속받음
//CrudRepository : Entity 매개체를 create, read, update, delete 기능을 하는 인터 페이스
//extends CrudRepository<Board, Long>의 매개변수 Board(Entity)와 Long(PK기본키의 타입)을 선언
//JPA가 어떤 객체로 어떤 타입으로 찾아야되는지 알아들음
public interface BoardRepository extends CrudRepository<Board, Long> {
}

