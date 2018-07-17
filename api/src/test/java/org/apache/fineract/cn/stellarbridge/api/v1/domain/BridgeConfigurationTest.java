/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.cn.stellarbridge.api.v1.domain;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.fineract.cn.test.domain.ValidationTest;
import org.apache.fineract.cn.test.domain.ValidationTestCase;
import org.junit.runners.Parameterized;

public class BridgeConfigurationTest extends ValidationTest<BridgeConfiguration> {

  public BridgeConfigurationTest(ValidationTestCase<BridgeConfiguration> testCase) {
    super(testCase);
  }

  @Override
  protected BridgeConfiguration createValidTestSubject() {
    return new BridgeConfiguration("xxxx", "yyy", "zzz");
  }

  @Parameterized.Parameters
  public static Collection testCases() {
    final Collection<ValidationTestCase> ret = new ArrayList<>();
    ret.add(new ValidationTestCase<BridgeConfiguration>("basicCase")
            .adjustment(x -> {})
            .valid(true));
    ret.add(new ValidationTestCase<BridgeConfiguration>("nullIdentifier")
            .adjustment(x -> x.setFineractIncomingAccountIdentifier(null))
            .valid(false));
    ret.add(new ValidationTestCase<BridgeConfiguration>("tooShortIdentifier")
            .adjustment(x -> x.setFineractIncomingAccountIdentifier("z"))
            .valid(false));
    ret.add(new ValidationTestCase<BridgeConfiguration>("tooLongPayload")
            .adjustment(x -> x.setFineractIncomingAccountIdentifier(RandomStringUtils.randomAlphanumeric(513)))
            .valid(false));
    return ret;
  }

}