package com.ingcore.common.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;

/**
 * xml与Obj相互转换
 * @author Lomis
 *
 */
public class XmlUtils {
	// 多线程安全的xstream
	private XStream xstream;

	public XmlUtils(Class<?>... clzs) {
		xstream = new XStream();
		for (Class<?> clz : clzs) {
			String clzName = clz.getName();
			xstream.alias(clzName.substring(clzName.lastIndexOf(".") + 1), clz);
		}
	}

	public XmlUtils(Class<?> clz, boolean annotation) {
		xstream = new XStream();
		xstream.processAnnotations(clz);
	}

	/**
	 * xml转Obj
	 * @param xml
	 * @return
	 */
	public Object fromXML(String xml) {
		return xstream.fromXML(xml);
	}

	public String toXML(Object obj) {
		return xstream.toXML(obj);
	}

	/**
	 *  将传入xml文本转换成Java对象
	 * @Title: toBean
	 * @Description: TODO
	 * @param xmlStr
	 * @param cls  xml对应的class类
	 * @return T   xml对应的class类的实例对象
	 *
	 * 调用的方法实例：PersonBean person=XmlUtil.toBean(xmlStr, PersonBean.class);
	 */
	public static <T> T  toBean(String xmlStr,Class<T> cls){
		XStream xstream = new XStream() {//忽略xml中的未知节点
			@Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {
				return new MapperWrapper(next) {
					@SuppressWarnings("rawtypes")
					@Override
					public boolean shouldSerializeMember(Class definedIn,
														 String fieldName) {
						if (definedIn == Object.class) {
							return false;
						}
						return super.shouldSerializeMember(definedIn, fieldName);
					}
				};
			}
		};
		xstream.processAnnotations(cls);
		@SuppressWarnings("unchecked")
		T obj=(T)xstream.fromXML(xmlStr);
		return obj;
	}

}
