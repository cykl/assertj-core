/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.core.error;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.error.ShouldHaveNoSuppressedException.shouldHaveNoSuppressedException;

import java.io.IOException;

import org.assertj.core.internal.TestDescription;
import org.assertj.core.presentation.Representation;
import org.assertj.core.presentation.StandardRepresentation;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link ShouldHaveNoSuppressedException#shouldHaveNoSuppressedException(Throwable)}
 * 
 * @author Clément MATHIEU
 */
public class ShouldHaveNoSuppressedException_create_Test {

  private TestDescription description;
  private Representation representation;

  @Before
  public void setup() {
    description = new TestDescription("Test");
    representation = new StandardRepresentation();
  }

  @Test
  public void should_create_error_message_for_Throwable_with_several_suppressed() {
    Throwable throwable = new Throwable();
    throwable.addSuppressed(new IOException());
    throwable.addSuppressed(new ArithmeticException());

    ErrorMessageFactory factory = shouldHaveNoSuppressedException(throwable);
    String actualMessage = factory.create(description, representation);

    String expectedMessage = String.format("[Test] %n" +
                                           "Expecting exception without suppressed exception, but suppressed was:" +
                                           "<[java.io.IOException, java.lang.ArithmeticException]>");

    assertThat(actualMessage).isEqualTo(expectedMessage);
  }
}
