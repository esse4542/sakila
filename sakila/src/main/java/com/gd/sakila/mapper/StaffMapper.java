package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.PageParam;
import com.gd.sakila.vo.Staff;
import com.gd.sakila.vo.StaffList;

/*
 * @Componetn, @Repository, @Service, @Controller -> Bean -> spring.getBean(클래스타입), @AutoWired <- Dependecy Inject
 * @Mapper : mybaits의 애노테이션 -> @Repository의 역할 -> 맵퍼 + 인터페이스 -> 컴파일(?) 시 구현클래스 자동으로 생성 
 */

@Mapper // mapper.xml을 찾아서 매소드명과 mapper의 id명의 같으면 합쳐서 매소드 구현클래스 생성시 오버라이딩을 한다.
public interface StaffMapper {
	
	// staff 로그인
	Staff selectStaffByLogin(Staff staff);
}
