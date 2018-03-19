package ${packageName}.controller.${moduleName};

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ingcore.common.service.CommonService;
import com.ingcore.order.service.${moduleName}.${ClassName}Service;



/**
 * ${functionName}Controller
 * @author ${classAuthor}
 * @version ${classVersion}
 */
@RestController
@RequestMapping(value = "/${urlPrefix}")
public class ${ClassName}Controller {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private ${ClassName}Service ${className}Service;
	


}
