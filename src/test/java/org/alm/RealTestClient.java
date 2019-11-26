package org.alm;

import junit.framework.Assert;
import org.alm.model.*;
import org.testng.annotations.Test;

import static org.alm.Util.readIntegrationAlmProperties;

public class RealTestClient {
    private static Config config;
    private static Client client;
    private TestSet testSet;
    private TestInstances testInstances;
    private org.alm.model.Test test;
    private Run run;

    @Test
    public void loadTest() throws Exception {
        config = new Config(readIntegrationAlmProperties());
        client = new Client(config);
        client.login();
        TestInstance testInstance = client.loadTestInstance("130");
        org.alm.model.Test test = client.loadTest(testInstance);
        Assert.assertNotNull(test);
    }

    @Test
    public void loadTestSet() throws Exception {
        config = new Config(readIntegrationAlmProperties());
        client = new Client(config);
        client.login();
        TestSet testSet = client.loadTestSet("201");
        Assert.assertNotNull(testSet);
    }

    @Test
    public void loadTestInstance() throws Exception {
        config = new Config(readIntegrationAlmProperties());
        client = new Client(config);
        client.login();
        TestInstance testInstance = client.loadTestInstance("130");
        Assert.assertNotNull(testInstance);
    }


    @Test
    public void createRunForTestInstanceAndTest_Ok() throws Exception {
        config = new Config(readIntegrationAlmProperties());
        client = new Client(config);
        client.login();
        TestInstance testInstance = client.loadTestInstance("131");
        org.alm.model.Test test = client.loadTest(testInstance);


        Run createdRun = client.createRun(testInstance, test);
        Assert.assertNotNull(createdRun);

        /*RunSteps runSteps = new RunSteps();
            RunStep runStep = new RunStep();
            runStep.status(Run.STATUS_PASSED);
            runStep.runId(createdRun.id());
            //runStep.executionTime(dateFormat.format(new Date()));
            runStep.description("Executed by script" + dateFormat.format(new Date()));
            //runStep.testId(test.id());
            runStep.expected("Expected");
            runSteps.addEntity(runStep);

        client.createRunSteps(createdRun, runSteps)*/
        ;
        RunSteps runSteps = client.loadRunSteps(createdRun.id());
        RunStep runStep = runSteps.entities().get(0);
        //runStep.clearBeforeUpdate();
        //runStep.executionTime(dateFormat.format(new Date()));
       runStep.status(Run.STATUS_PASSED);
        runStep.actual();

        client.updateRunStep(runStep);

        Assert.assertNotNull(runSteps);
    }

    @Test
    public void updateTestInstanceAndTest_Ok() throws Exception {
        config = new Config(readIntegrationAlmProperties());
        client = new Client(config);
        client.login();
        TestInstance testInstance = client.loadTestInstance("131");
        testInstance.status(Run.STATUS_NO_RUN);
        TestInstance updatedTestInstance = client.updateTestInstance(testInstance);

        Assert.assertNotNull(updatedTestInstance);
    }

    @Test
    public void updateTestInstanceFromLoadTestInstanceByName_Ok() throws Exception {
        config = new Config(readIntegrationAlmProperties());
        client = new Client(config);
        client.login();
            TestInstance testInstance = client.loadTestInstanceByName("T0001 [1]");
        testInstance.status(Run.STATUS_NO_RUN);
        TestInstance updatedTestInstance = client.updateTestInstance(testInstance);

        Assert.assertNotNull(updatedTestInstance);
    }
}
