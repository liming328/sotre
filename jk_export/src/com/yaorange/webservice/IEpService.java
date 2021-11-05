package com.yaorange.webservice;

import javax.jws.WebService;

@WebService
public interface IEpService {
	public String exportE(String jsonStr) throws Exception;
}
