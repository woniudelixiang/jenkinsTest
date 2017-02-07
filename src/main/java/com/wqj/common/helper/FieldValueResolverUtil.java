package com.wqj.common.helper;

import java.util.ArrayList;
import java.util.List;
import com.wqj.common.helper.pojo.resolver.FieldValueResolver;
import com.wqj.common.helper.pojo.resolver.impl.ListFieldValueResolver;
import com.wqj.common.helper.pojo.resolver.impl.MapFieldValueResolver;
import com.wqj.common.helper.pojo.resolver.impl.ModelAttributeFillProcessor;

public class FieldValueResolverUtil {

	// 所有的解析器
	private static  List<FieldValueResolver> resolvers = new ArrayList<FieldValueResolver>();
	// 自定义的解析器
	private static List<FieldValueResolver> customResolvers = new ArrayList<FieldValueResolver>();
	
	public static List<FieldValueResolver> getResolvers() {
		getDefaultResolvers();
		return resolvers;
	}
	
	private static List<FieldValueResolver> getDefaultResolvers(){
		
		resolvers.add(new ListFieldValueResolver());
		resolvers.add(new MapFieldValueResolver());
		
		// Custom Resolver
		if (getCustomResolvers() != null) {
			resolvers.addAll(getCustomResolvers());
		}
		
		// Catch-all
		resolvers.add(new ModelAttributeFillProcessor(true));
		return resolvers;
	}
	
	private static List<FieldValueResolver> getCustomResolvers(){
		return customResolvers;
	}
	
	// 注册自定义解析器
	public static void register(FieldValueResolver resolver) {
		customResolvers.add(resolver);
    }
	
}
