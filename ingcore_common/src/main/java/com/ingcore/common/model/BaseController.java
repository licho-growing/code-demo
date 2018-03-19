package com.ingcore.common.model;

import com.ingcore.common.constants.ResultConstants;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangbin on 2017/11/21
 */
public class BaseController {

	static {
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
	}

	@InitBinder
	protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	public <T> Result<T> createSuccessResult(T resultData) {
		Result<T> result = new Result<>();
		result.setResultData(resultData);
		return result;
	}
	public Result createFailedResult(String resultDesc) {
		Result result = new Result();
		result.setResultCode(ResultConstants.RESULT_CODE_FAILED);
		result.setResultDesc(resultDesc);
		return result;
	}
	public <T> Result<T> createFailedResult(String resultDesc, T resultData) {
		return createResult(ResultConstants.RESULT_CODE_FAILED, resultDesc, resultData);
	}

	public <T> Result<T> createResult(String resultCode, String resultDesc, T resultData) {
		Result<T> result = new Result<>();
		result.setResultCode(resultCode);
		result.setResultData(resultData);
		result.setResultDesc(resultDesc);
		return result;
	}

	public <T> PageResult<T> createSuccessPageResult(GenericPage<T> resultData) {
		PageResult<T> result = new PageResult<>();
		result.setResultData(resultData);
		return result;
	}
	public PageResult createFailedPageResult(String resultDesc) {
		PageResult result = new PageResult();
		result.setResultCode(ResultConstants.RESULT_CODE_FAILED);
		result.setResultDesc(resultDesc);
		return result;
	}
	public <T> PageResult<T> createFailedPageResult(String resultDesc, GenericPage<T> resultData) {
		PageResult<T> result = new PageResult<>();
		result.setResultCode(ResultConstants.RESULT_CODE_FAILED);
		result.setResultDesc(resultDesc);
		result.setResultData(resultData);
		return result;
	}

}
