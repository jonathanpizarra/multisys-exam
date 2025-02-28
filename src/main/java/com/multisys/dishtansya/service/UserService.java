package com.multisys.dishtansya.service;

import com.multisys.dishtansya.req.RegisterReq;
import com.multisys.dishtansya.res.RegisterRes;

public interface UserService {

    RegisterRes registerUser(RegisterReq req);
}
