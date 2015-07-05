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

/**
 * Creates an error message indicating that an assertion that verifies that an {@link Throwable} has no suppressed
 * exception failed.
 * 
 * @author Clément MATHIEU
 */
public class ShouldHaveNoSuppressedException extends BasicErrorMessageFactory {

  /**
   * Creates a new </code>{@link ShouldHaveNoSuppressedException}</code>.
   * @param actual the actual value in the failed assertion.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldHaveNoSuppressedException(Throwable actual) {
    return new ShouldHaveNoSuppressedException(actual);
  }

  private ShouldHaveNoSuppressedException(Throwable actual) {
    super("%nExpecting exception without suppressed exception, but suppressed was:<%s>", suppressedAsObject(actual));
  }

  private static Object suppressedAsObject(Throwable actual) {
    return actual.getSuppressed();
  }
}
