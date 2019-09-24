package cn.zhengsigen.test3;

import java.util.ArrayList;

public interface UserManager {

	void query();
	ArrayList<Acount> queryByClass();
	ArrayList<Acount> queryByName();
}
