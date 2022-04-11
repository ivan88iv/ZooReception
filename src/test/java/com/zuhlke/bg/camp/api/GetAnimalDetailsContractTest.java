package com.zuhlke.bg.camp.api;

import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(pactVersion = PactSpecVersion.V3)
public class GetAnimalDetailsContractTest {

    private static final String CONSUMER_NAME = "ZooReception";

    private static final String PROVIDER_NAME = "ZooManager";

    // TODO add your consumer contract tests here
}
