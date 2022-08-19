package test.testng01.testng;

import org.testng.annotations.*;

public class TestNGHook02 {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("\t ---> beforeTest");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("\t\t ---> beforeClass");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("\t\t\t---> beforeMethod");
    }
    @Test
    public void testSth(){
        System.out.println("\t\t\t\t---> TEST STH");
    }
    @Test
    public void testSthElse(){
        System.out.println("\t\t\t\t---> TEST STH ELSE");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("\t\t\t---> afterMethod");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("\t\t\t---> afterClass");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("\t ---> afterTest");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite");
    }
}

