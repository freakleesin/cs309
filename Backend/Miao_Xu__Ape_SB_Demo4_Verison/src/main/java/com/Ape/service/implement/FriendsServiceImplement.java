/**
 * 
 */
package com.Ape.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ape.controller.viewObject.UsersVO;
import com.Ape.dao.DO_FriendsMapper;
import com.Ape.dao.DO_UsersMapper;
import com.Ape.dataObject.DO_Friends;
import com.Ape.error.BusException;
import com.Ape.error.EnumBusError;
import com.Ape.service.FriendsService;
import com.Ape.service.UsersService;

/**
 * @author Miao Xu
 *
 */

@Service
public class FriendsServiceImplement implements FriendsService {

	@Autowired
	private DO_FriendsMapper do_FriendsMapper;

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private DO_UsersMapper do_UsersMapper;

	@Override
	@Transactional
	public void addFriends(Integer usersId, String friends) throws BusException {

		if(do_UsersMapper.selectByUsername(friends) == null) {
			throw new BusException(EnumBusError.USER_NOT_EXISIS);
		}
		if (do_FriendsMapper.selectByPrimaryKey(usersId) == null) {
			DO_Friends do_Friends = new DO_Friends();
			do_Friends.setUsersid(usersId);
			do_Friends.setFriends1(friends);
			do_FriendsMapper.insertSelective(do_Friends);
			return;
		} else {
			DO_Friends do_Friends = do_FriendsMapper.selectByPrimaryKey(usersId);
			if (do_Friends.getFriends2().equals("" + '"')) {
				do_Friends.setFriends2(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends3().equals("" + '"')) {
				do_Friends.setFriends3(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends4().equals("" + '"')) {
				do_Friends.setFriends4(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends5().equals("" + '"')) {
				do_Friends.setFriends5(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends6().equals("" + '"')) {
				do_Friends.setFriends6(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends7().equals("" + '"')) {
				do_Friends.setFriends7(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends8().equals("" + '"')) {
				do_Friends.setFriends8(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends9().equals("" + '"')) {
				do_Friends.setFriends9(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends10().equals("" + '"')) {
				do_Friends.setFriends10(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends11().equals("" + '"')) {
				do_Friends.setFriends11(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends12().equals("" + '"')) {
				do_Friends.setFriends12(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends13().equals("" + '"')) {
				do_Friends.setFriends13(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends14().equals("" + '"')) {
				do_Friends.setFriends14(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends15().equals("" + '"')) {
				do_Friends.setFriends15(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends16().equals("" + '"')) {
				do_Friends.setFriends16(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends17().equals("" + '"')) {
				do_Friends.setFriends17(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends18().equals("" + '"')) {
				do_Friends.setFriends18(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends19().equals("" + '"')) {
				do_Friends.setFriends19(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
			if (do_Friends.getFriends20().equals("" + '"')) {
				do_Friends.setFriends20(friends);
				do_FriendsMapper.updateByPrimaryKeySelective(do_Friends);
				return;
			}
		}
	}

	@Override
	@Transactional
	public List<UsersVO> listUsersVOOfFriendsModelByUsersId(Integer usersId) {

		DO_Friends do_Friends = do_FriendsMapper.selectByPrimaryKey(usersId);
		List<UsersVO> listUsersVOOfFriendsModelByUsersId = new ArrayList<UsersVO>();
		if (do_Friends.getFriends1().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;
		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends1()));
		}
		if (do_Friends.getFriends2().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;
		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends2()));
		}
		if (do_Friends.getFriends3().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;
		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends3()));
		}
		if (do_Friends.getFriends4().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;
		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends4()));
		}
		if (do_Friends.getFriends5().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;

		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends5()));
		}
		if (do_Friends.getFriends6().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;

		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends6()));
		}
		if (do_Friends.getFriends7().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;

		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends7()));
		}
		if (do_Friends.getFriends8().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;

		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends8()));
		}
		if (do_Friends.getFriends9().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;

		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends9()));
		}
		if (do_Friends.getFriends10().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;

		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends10()));
		}
		if (do_Friends.getFriends11().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;

		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends11()));
		}
		if (do_Friends.getFriends12().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;

		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends12()));
		}
		if (do_Friends.getFriends13().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;

		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends13()));
		}
		if (do_Friends.getFriends14().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;

		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends14()));
		}
		if (do_Friends.getFriends15().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;

		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends15()));
		}
		if (do_Friends.getFriends16().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;

		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends16()));
		}
		if (do_Friends.getFriends17().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;

		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends17()));
		}
		if (do_Friends.getFriends18().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;

		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends18()));
		}
		if (do_Friends.getFriends19().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;

		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends19()));
		}
		if (do_Friends.getFriends20().equals("" + '"')) {
			return listUsersVOOfFriendsModelByUsersId;

		} else {
			listUsersVOOfFriendsModelByUsersId.add(usersService.selectByUsername(do_Friends.getFriends20()));
		}
		return listUsersVOOfFriendsModelByUsersId;
	}
}
