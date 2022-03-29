package Testcase_1;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class Listener implements ITestListener {

        @Override
        public void onStart(ITestContext args)
        {
            System.out.println("Start Test Execution");
        }
        @Override
        public void onFinish(ITestContext args)
        {
            System.out.println("Finish Test Execution");
        }
        @Override
        public void onTestStart(ITestResult args)
        {
            System.out.println("Test Starting");
        }
        @Override
        public void onTestSkipped(ITestResult args)
        {
            System.out.println("Test Skipped");
        }
        @Override
        public void onTestSuccess(ITestResult args)
        {
            System.out.println("Test Case Executed Successfully");
        }
        @Override
        public void onTestFailure(ITestResult args)
        {
            System.out.println("Test Case Failure");
        }
    }
