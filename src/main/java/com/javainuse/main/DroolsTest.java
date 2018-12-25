package com.javainuse.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;

import com.javainuse.model.Product;

public class DroolsTest {
	public static void main(String[] args) throws DroolsParserException, IOException {
		DroolsTest droolsTest = new DroolsTest();
		droolsTest.executeDrools();
	}
	
	public void executeDrools() throws DroolsParserException, IOException {
		PackageBuilder packageBuilder=   new PackageBuilder();
		String ruleFile = "/com/rule/rule.drl";
//		System.out.println("ruleFile = "+ruleFile);
		
		InputStream resourceAsStream = getClass().getResourceAsStream(ruleFile);
		System.out.println("rulresourceAsStreameFile = "+resourceAsStream);
		Reader reader = new InputStreamReader(resourceAsStream);
		packageBuilder.addPackageFromDrl(reader);
		org.drools.core.rule.Package rulePackage = packageBuilder.getPackage();
		
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		ruleBase.addPackage(rulePackage);
		
		WorkingMemory workingMemory = ruleBase.newStatefulSession();
		
		Product product = new Product();
		product.setType("gold");
		
		workingMemory.insert(product);
		workingMemory.fireAllRules();
		
		System.out.println("The discount for the product " + product.getType() + " is " + product.getDiscount());
		
	}

}
