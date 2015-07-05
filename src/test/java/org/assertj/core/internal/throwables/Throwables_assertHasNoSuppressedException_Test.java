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
package org.assertj.core.internal.throwables;

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.error.ShouldHaveNoSuppressedException.shouldHaveNoSuppressedException;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Throwables;
import org.assertj.core.internal.ThrowablesBaseTest;
import org.junit.Test;

/**
 * Tests for <code>{@link Throwables#assertHasNoSuppressedException(AssertionInfo, Throwable)}</code>
 *
 * @author Clément MATHIEU
 */
public class Throwables_assertHasNoSuppressedException_Test extends ThrowablesBaseTest {

  @Test
  public void should_pass_if_actual_has_no_suppressed() {
    throwables.assertHasNoSuppressedException(someInfo(), actual);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    throwables.assertHasNoSuppressedException(someInfo(), null);
  }

  @Test
  public void should_fail_if_actual_has_at_least_one_suppressed() {
    AssertionInfo info = someInfo();
    Throwable throwableWithCause = new Throwable();
    throwableWithCause.addSuppressed(new Throwable());
    try {
      throwables.assertHasNoSuppressedException(info, throwableWithCause);
      fail("AssertionError expected");
    } catch (AssertionError err) {
      verify(failures).failure(info, shouldHaveNoSuppressedException(throwableWithCause));
    }
  }
}
