/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package retrofit2.adapter.rxjava;

import java.io.IOException;
import org.junit.Test;
import retrofit2.Response;
import retrofit2.adapter.rxjava.RequestResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public final class ResultTest {
  @Test public void response() {
    Response<String> response = Response.success("Hi");
    RequestResult<String> result = RequestResult.response(response);
    assertThat(result.isError()).isFalse();
    assertThat(result.error()).isNull();
    assertThat(result.response()).isSameAs(response);
  }

  @Test public void nullResponseThrows() {
    try {
      RequestResult.response(null);
      fail();
    } catch (NullPointerException e) {
      assertThat(e).hasMessage("response == null");
    }
  }

  @Test public void error() {
    Throwable error = new IOException();
    RequestResult<Object> result = RequestResult.error(error);
    assertThat(result.isError()).isTrue();
    assertThat(result.error()).isSameAs(error);
    assertThat(result.response()).isNull();
  }

  @Test public void nullErrorThrows() {
    try {
      RequestResult.error(null);
      fail();
    } catch (NullPointerException e) {
      assertThat(e).hasMessage("error == null");
    }
  }
}
