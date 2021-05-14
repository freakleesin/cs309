package com.demo.moments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;




public interface MomentsRepository extends JpaRepository<Moments, Integer> {

	@Query(value = "select * FROM moments where moments.userid=?1 order by moments.time DESC", nativeQuery = true)
	public List<Moments> findByUserid(Integer userId);

	@Query(value = "select distinct m.id, m.userid, m.text, m.imageURL, m.mlike, m.mdislike, m.time, m.image2\r\n"
			+ "from moments m inner join friend f\r\n"
			+ "on  f.friendid= m.userid or m.userid =?1  where f.users_id =?1 order by m.id DESC", nativeQuery = true)
	List<Moments> getUserAllMoment(Integer userid);

}
